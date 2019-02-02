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

import java.time.LocalDateTime;
import java.util.UUID;
import static java.util.stream.Collectors.toList;


/**
 * Created by spasoje on 01-Nov-18.
 */
@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventFinder eventFinder;

    @Autowired
    private Gate gate;

    @GetMapping
    //TODO Use Resources instead of ResponseEntity? Check this out: https://stackoverflow.com/questions/28139856/how-can-i-get-spring-mvchateoas-to-encode-a-list-of-resources-into-hal
    public ResponseEntity<Iterable<EventResource>> getEvents(@RequestParam(required = false) String eventName,
                                                             @RequestParam(required = false) String eventType,
                                                             @RequestParam(required = false) UUID hostId,
                                                             @RequestParam(required = false) UUID placeId,
                                                             @RequestParam(required = false) LocalDateTime timeFrom,
                                                             @RequestParam(required = false) LocalDateTime timeTo) {
        return ResponseEntity.ok().body(eventFinder.findByExample(EventFilter.builder()
                .eventName(eventName)
                .eventType(eventType)
                .hostId(hostId)
                .placeId(placeId)
                .timeFrom(LocalDateTime.of(1993,12,2,10,15))
                .timeTo(LocalDateTime.of(1999,12,2,10,15))
                .build())
                .stream()
                .map(EventResource::fromEvent)
                .collect(toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResource> getEvent(@PathVariable UUID id) {
        return ResponseEntity.ok().body(EventResource.fromEvent(eventFinder.findById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventResource> insertEvent(@RequestBody CreateEventRequest createEventRequest) {
        Event createdEvent = gate.dispatch(CreateEvent
                .builder()
                .description(createEventRequest.getDescription())
                .eventDateTime(createEventRequest.getEventDateTime())
                .eventName(createEventRequest.getEventName())
                .eventType(createEventRequest.getEventType())
                .placeId(createEventRequest.getPlaceId())
                .source(createEventRequest.getSource())
                .build());
        return ResponseEntity.ok(EventResource.fromEvent(createdEvent));
    }
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventResource> updateEvent(@PathVariable UUID id, @RequestBody UpdateEventRequest updateEventRequest) {
        Event updatedEvent = gate.dispatch(UpdateEvent
                .builder()
                .id(id)
                .description(updateEventRequest.getDescription())
                .eventDateTime(updateEventRequest.getEventDateTime())
                .eventName(updateEventRequest.getEventName())
                .eventType(updateEventRequest.getEventType())
                .placeId(updateEventRequest.getPlaceId())
                .source(updateEventRequest.getSource())
                .build());
        return ResponseEntity.ok(EventResource.fromEvent(updatedEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        gate.dispatch(DeleteEvent
                .builder()
                .id(id)
                .build());
        return ResponseEntity.ok().build();
    }
}
