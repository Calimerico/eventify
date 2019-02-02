package com.eventify.events.infrastructure;

import com.eventify.events.api.rest.EventFilter;
import com.eventify.events.domain.Event;
import com.eventify.events.domain.EventFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public Event findById(UUID id) {
        return eventRepository.findByEventId(id);//TODO Read this https://tuhrig.de/anti-pattern-dont-use-optionals-for-data-repositories/ Change to Optional<Event> or not?
    }

    public List<Event> findByExample(EventFilter eventFilter) {
        Example<Event> example = Example.of(EventFactory
                .aEvent()
                .eventName(eventFilter.getEventName())
                .eventType(eventFilter.getEventType())
                .build());//TODO Places and hosts filter?
        return eventRepository.findAll(example)
                .stream()
                .filter(event -> event.getEventDateTime() == null || event.getEventDateTime().isAfter(eventFilter.getTimeFrom()))
                .filter(event -> event.getEventDateTime() == null || event.getEventDateTime().isBefore(eventFilter.getTimeTo()))
                //TODO This solution with filtering is a little bit hacky since we should somehow filter events in sql query, not here
                .collect(Collectors.toList());
    }

    public List<Event> findAll() {
        List<Event> eventslist = new ArrayList<>();
        //TODO This conversion stupid as hell
        Iterable<Event> events = eventRepository.findAll();
        events.forEach(eventslist::add);
        return eventslist;
    }
}
