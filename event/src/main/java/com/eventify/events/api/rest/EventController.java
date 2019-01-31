package com.eventify.events.api.rest;

import com.eventify.events.application.commands.CreateEvent;
import com.eventify.events.application.commands.DeleteEvent;
import com.eventify.events.application.commands.UpdateEvent;
import com.eventify.events.domain.Event;
import com.eventify.events.domain.EventFactory;
import com.eventify.events.infrastructure.EventFinder;
import com.eventify.shared.demo.Gate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import static java.util.stream.Collectors.toList;


/**
 * Created by spasoje on 01-Nov-18.
 */
@RestController("/events")
public class EventController {

    @Autowired
    private EventFinder eventFinder;

    @Autowired
    private Gate gate;

    @GetMapping
    public ResponseEntity<Iterable<EventResource>> getEvents(@RequestParam(required = false) String eventName, @RequestParam(required = false) String eventType) {
        return ResponseEntity.ok().body(eventFinder.findByExample(EventFactory.aEvent()
                .eventName(eventName)
                .eventType(eventType)
                .build())
                .stream()
                .map(EventResource::fromEvent)
                .collect(toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResource> getEvent(@PathVariable UUID eventId) {
        return ResponseEntity.ok().body(EventResource.fromEvent(eventFinder.findById(eventId.toString())));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertEvent(@RequestBody CreateEventRequest createEventRequest) {
        gate.dispatch(CreateEvent
                .builder()
                .description(createEventRequest.getDescription())
                .eventDateAndTime(createEventRequest.getEventDateAndTime())
                .eventName(createEventRequest.getEventName())
                .eventType(createEventRequest.getEventType())
                .placeId(createEventRequest.getPlaceId())
                .source(createEventRequest.getSource())
                .build());
    }
    @PutMapping
    public void updateEvent(@RequestBody UpdateEventRequest updateEventRequest) {
        gate.dispatch(UpdateEvent
                .builder()
                .description(updateEventRequest.getDescription())
                .eventDateAndTime(updateEventRequest.getEventDateAndTime())
                .eventName(updateEventRequest.getEventName())
                .eventType(updateEventRequest.getEventType())
                .placeId(updateEventRequest.getPlaceId())
                .source(updateEventRequest.getSource())
                .build());
    }

    @DeleteMapping
    //TODO Check in all controllers, all should return HttpEntity, not void or somthing like that
    public void deleteEvent(@PathVariable String eventId) {
        gate.dispatch(DeleteEvent
                .builder()
                .eventId(eventId)
                .build());
    }
}
