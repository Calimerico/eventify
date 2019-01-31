package com.eventify.events.infrastructure;

import com.eventify.events.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    public Event findById(String id) {
        return eventRepository.findByEventId(id);//TODO Read this https://tuhrig.de/anti-pattern-dont-use-optionals-for-data-repositories/ Change to Optional<Event> or not?
    }

    public List<Event> findByExample(Event event) {
        Example<Event> example = Example.of(event);
        return eventRepository.findAll(example);
    }

    public List<Event> findAll() {
        List<Event> eventslist = new ArrayList<>();
        //TODO This conversion stupid as hell
        Iterable<Event> events = eventRepository.findAll();
        events.forEach(eventslist::add);
        return eventslist;
    }
}
