package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Created by spasoje on 01-Nov-18.
 */
@RestController
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/events")
    public ResponseEntity<Iterable<Event>> getEvents() {
        return ResponseEntity.ok().body(eventRepository.findAll());
    }

    @PostMapping(value = "/addEvent",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertEvent(@RequestBody EventBean eventBean) {
        Event event = EventFactory.create(eventBean.getEventType());
        event.setEventId(String.valueOf(System.currentTimeMillis()));//TODO
        event.setEventName(eventBean.getEventName());
        event.setEventType(eventBean.getEventType());
        event.setEventDateAndTime(ZonedDateTime.of(eventBean.getEventDate(),eventBean.getEventTime(), ZoneId.of("ECT")));
        eventRepository.save(event);

    }
    @PatchMapping(value = "/updateEvent")
    public void updateEvent(@RequestBody UpdateEventRequest updateEventRequest) {

    }

    @GetMapping
    public String dummyHello() {//TODO This is just for example
        return restTemplate.getForObject("http://dummy-service/dummy/", String.class) + "from event module!";
    }
}
