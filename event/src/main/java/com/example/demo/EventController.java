package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    public Iterable<Event> getEvents() {
        return eventRepository.findAll();
    }

    @GetMapping
    public String dummyHello() {//TODO This is just for example
        return restTemplate.getForObject("http://dummy-service/dummy/", String.class) + "from event module!";
    }
}
