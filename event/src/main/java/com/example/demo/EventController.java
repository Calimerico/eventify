package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


/**
 * Created by spasoje on 01-Nov-18.
 */
@RestController
public class EventController {

    @Autowired
    private EventFinder eventFinder;

    @Autowired
    private Gate gate;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/events")
    public ResponseEntity<Iterable<Event>> getEvents(@RequestParam(required = false) String eventName, @RequestParam(required = false) String eventType) {

        //TODO Domain object should not be exposed!
        return ResponseEntity.ok().body(eventFinder.findAll());
    }

    @PostMapping(value = "/addEvent",  consumes = MediaType.APPLICATION_JSON_VALUE)
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
    @PatchMapping(value = "/updateEvent")//TODO You should check for null values and escape them so this can behave like PATHC. Now its like PUT!
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

    @GetMapping
    public String dummyHello() {//TODO This is just for example
        return restTemplate.getForObject("http://dummy-service/dummy/", String.class) + "from event module!";
    }

    @DeleteMapping(value = "/deleteEvent")
    //TODO Check in all controllers, all should return HttpEntity, not void or somthing like that
    public void deleteEvent(@PathVariable String eventId) {
        gate.dispatch(DeleteEvent
                .builder()
                .eventId(eventId)
                .build());
    }
}
