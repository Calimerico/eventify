package com.eventify.events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spasoje on 16-Dec-18.
 */
@Component
@RequiredArgsConstructor
public class EventFinder {
    private final EventRepository eventRepository;

    public Event findByName(String eventName) {
        return eventRepository.findByEventName(eventName);
    }

    public List<Event> findAll() {
        List<Event> eventslist = new ArrayList<>();
        //TODO This conversion stupid as hell
        Iterable<Event> events = eventRepository.findAll();
        events.forEach(eventslist::add);
        return eventslist;
    }
}
