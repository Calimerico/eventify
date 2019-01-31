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
    //TODO Use Resources instead of ResponseEntity? Check this out: https://stackoverflow.com/questions/28139856/how-can-i-get-spring-mvchateoas-to-encode-a-list-of-resources-into-hal
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
    public ResponseEntity<EventResource> insertEvent(@RequestBody CreateEventRequest createEventRequest) {
        Event createdEvent = gate.dispatch(CreateEvent
                .builder()
                .description(createEventRequest.getDescription())
                .eventDateAndTime(createEventRequest.getEventDateAndTime())
                .eventName(createEventRequest.getEventName())
                .eventType(createEventRequest.getEventType())
                .placeId(createEventRequest.getPlaceId())
                .source(createEventRequest.getSource())
                .build());
        return ResponseEntity.ok(EventResource.fromEvent(createdEvent));
    }
    @PutMapping
    public ResponseEntity<EventResource> updateEvent(@PathVariable UUID id, @RequestBody UpdateEventRequest updateEventRequest) {
        Event updatedEvent = gate.dispatch(UpdateEvent
                .builder()
                .id(id)
                .description(updateEventRequest.getDescription())
                .eventDateAndTime(updateEventRequest.getEventDateAndTime())
                .eventName(updateEventRequest.getEventName())
                .eventType(updateEventRequest.getEventType())
                .placeId(updateEventRequest.getPlaceId())
                .source(updateEventRequest.getSource())
                .build());
        return ResponseEntity.ok(EventResource.fromEvent(updatedEvent));
    }

    @DeleteMapping
    //TODO Check in all controllers, all should return HttpEntity, not void or somthing like that
    //TODO Check this article https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/request-response-entity.html
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventId) {
        gate.dispatch(DeleteEvent
                .builder()
                .eventId(eventId)
                .build());
        return null;//TODO
    }
}
