package com.eventify.events.infrastructure;

import com.eventify.events.api.rest.EventFilter;
import com.eventify.events.domain.Event;
import com.eventify.events.domain.EventFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by spasoje on 16-Dec-18.
 */
@Component
@RequiredArgsConstructor
public class EventFinder {
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;

    public Event findByName(String eventName) {
        return eventRepository.findByEventName(eventName);
    }

    public Event findById(UUID id) {
        //TODO Read this https://tuhrig.de/anti-pattern-dont-use-optionals-for-data-repositories/ Change to Optional<Event> or not? Provide both method obtain and get
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Fix this runtime exception"));
    }

    public Page<Event> findByExample(EventFilter eventFilter, Pageable pageable) {//TODO Handle if dateFrom,dateTo,priceFrom and proceTo are null
        placeRepository.findAll();//TODO DON'T DELETE THIS LINE OR YOU WILL INTRODUCE BUG. THIS IS TEMPORARY SOLUTION
        //TODO For line above take a look here https://stackoverflow.com/questions/13539050/entitynotfoundexception-in-hibernate-many-to-one-mapping-however-data-exist
        //TODO https://stackoverflow.com/questions/39784344/check-date-between-two-other-dates-spring-data-jpa
        Example<Event> example = Example.of(EventFactory
                .aEvent()
                .eventName(eventFilter.getEventName())
                .eventType(eventFilter.getEventType())
                .build());//TODO Places and hosts filter?
        List<Event> events = eventRepository.findAll(example, pageable)
                .stream()
                .filter(event -> event.getEventDateTime() == null || event.getEventDateTime().isAfter(eventFilter.getTimeFrom()))
                .filter(event -> event.getEventDateTime() == null || event.getEventDateTime().isBefore(eventFilter.getTimeTo()))
                .filter(event -> event.getPrices() == null || event.getPrices().isEmpty() || event.getPrices().stream().filter(price -> price >= eventFilter.getPriceFrom()).collect(Collectors.toList()).size() > 0)
                .filter(event -> event.getPrices() == null || event.getPrices().isEmpty() || event.getPrices().stream().filter(price -> price <= eventFilter.getPriceTo()).collect(Collectors.toList()).size() > 0)
                //TODO This solution with filtering is a little bit hacky since we should somehow filter events in sql query, not here
                .collect(Collectors.toList());
        return new PageImpl<>(events,pageable,events.size());
    }

    public List<Event> findAll() {
        List<Event> eventslist = new ArrayList<>();
        //TODO This conversion stupid as hell
        Iterable<Event> events = eventRepository.findAll();
        events.forEach(eventslist::add);
        return eventslist;
    }
}
