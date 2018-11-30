package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
        ArrayList<Event> events = new ArrayList<>();
        Event event = new TheaterEvent();
        event.setEventDateAndTime(ZonedDateTime.of(LocalDate.of(2018,2,2), LocalTime.of(10,20),ZoneId.of("Europe/Paris")));
        event.setEventName("Some theater event");
        event.setDescription("DEsc of some event");
        event.setPlaceId("Sava centar");
        event.setSource("www.eventim.rs");
        events.add(event);

        Event event2 = new TheaterEvent();
        event2.setEventDateAndTime(ZonedDateTime.of(LocalDate.of(2018,3,3), LocalTime.of(11,20),ZoneId.of("Europe/Paris")));
        event2.setEventName("Some theater2 event");
        event2.setDescription("DEsc of some2 event");
        event2.setPlaceId("Dom kulture");
        event2.setSource("www.atelje212.rs");
        events.add(event2);
        return ResponseEntity.ok().body(events);
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
