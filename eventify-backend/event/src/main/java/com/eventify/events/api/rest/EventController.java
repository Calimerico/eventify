package com.eventify.events.api.rest;

import com.eventify.events.application.commands.CreateEvent;
import com.eventify.events.application.commands.DeleteEvent;
import com.eventify.events.application.commands.UpdateEvent;
import com.eventify.events.domain.Event;
import com.eventify.events.infrastructure.EventFinder;
import com.eventify.shared.demo.Gate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * Created by spasoje on 01-Nov-18.
 */
@RestController
@RequestMapping(value = EventController.BASE_PATH)
@RequiredArgsConstructor
public class EventController {

    public static final String BASE_PATH = "/events";
    public static final String ID_PATH = "/{id}";
    private final EventFinder eventFinder;
    private final Gate gate;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //TODO Use Resources instead of ResponseEntity? Check this out: https://stackoverflow.com/questions/28139856/how-can-i-get-spring-mvchateoas-to-encode-a-list-of-resources-into-hal
    public ResponseEntity<PagedResources<EventResource>> getEvents(@ModelAttribute EventFilterBean eventFilterBean,
                                                                   @PageableDefault Pageable pageable,
                                                                   PagedResourcesAssembler<Event> pagedAssembler) {
        Page<Event> pageOfEvents = eventFinder.findByExample(EventFilter.builder()
                .eventName(eventFilterBean.getEventName())
                .eventType(eventFilterBean.getEventType())
                .hostId(eventFilterBean.getHostId())
                .placeId(eventFilterBean.getPlaceId())
                .timeFrom(eventFilterBean.getTimeFrom())
                .timeTo(eventFilterBean.getTimeTo())
                .priceFrom(eventFilterBean.getPriceFrom())
                .priceTo(eventFilterBean.getPriceTo())
                .build(), pageable);

        return ResponseEntity.ok().body(pagedAssembler.toResource(pageOfEvents,new EventPagedResourcesAssembler()));
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<EventResource> getEvent(@PathVariable UUID id) {
        return ResponseEntity.ok().body(EventResource.fromEvent(eventFinder.findById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_REGISTERED_USER")
    public ResponseEntity<EventResource> insertEvent(@RequestBody CreateEventRequest createEventRequest) {
        Event createdEvent = gate.dispatch(CreateEvent
                .builder()
                .description(createEventRequest.getDescription())
                .eventDateTime(createEventRequest.getEventDateTime())
                .eventName(createEventRequest.getEventName())
                .eventType(createEventRequest.getEventType())
                .placeId(createEventRequest.getPlaceId())
                .source(createEventRequest.getSource())
                .profilePicture(createEventRequest.getProfilePicture())
                .prices(createEventRequest.getPrices())
                .build());
        return ResponseEntity.ok(EventResource.fromEvent(createdEvent));
    }

    @PutMapping(value = ID_PATH,consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_REGISTERED_USER")
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
                .prices(updateEventRequest.getPrices())
                .build());
        return ResponseEntity.ok(EventResource.fromEvent(updatedEvent));
    }

    @DeleteMapping(ID_PATH)
    @Secured("ROLE_REGISTERED_USER")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        gate.dispatch(DeleteEvent
                .builder()
                .id(id)
                .build());
        return ResponseEntity.ok().build();
    }

    @Component
    public class EventPagedResourcesAssembler extends ResourceAssemblerSupport<Event, EventResource> {

        public EventPagedResourcesAssembler() {
            super(EventController.class, EventResource.class);
        }

        @Override
        public EventResource toResource(Event event) {
            return EventResource.fromEvent(event);
        }
    }
}
