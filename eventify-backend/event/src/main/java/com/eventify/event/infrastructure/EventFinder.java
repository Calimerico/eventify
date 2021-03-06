package com.eventify.event.infrastructure;

import com.eventify.event.domain.Event;
import com.eventify.event.domain.EventBuilder;
import com.eventify.event.domain.EventRepository;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

/**
 * Created by spasoje on 16-Dec-18.
 */
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventFinder {
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final EventBuilder eventBuilder;

    public Event findByName(String eventName) {
        return eventRepository.findByEventName(eventName);
    }

    public Event findById(UUID id) {
        return eventRepository.loadById(id);
    }

    //todo this is findbyhostid!!!
    public Page<Event> findByUserId(UUID userId, Pageable pageable) {
        Example<Event> tExample = Example.of(eventBuilder.build());
        Page<Event> all = eventRepository.findAll(tExample, pageable);//todo we should create one findByUserId method and not user findall
        List<Event> myEvents = all.getContent()
                .stream()
                .filter(event -> event.isUserHostForThisEvent(userId))
                .collect(toList());
        return new PageImpl<>(myEvents,pageable,myEvents.size());
    }

    public Page<Event> findByExample(EventFilterDto eventFilter, Pageable pageable) {//TODO Handle if dateFrom,dateTo,priceFrom and proceTo are null
        placeRepository.findAll();//TODO DON'T DELETE THIS LINE OR YOU WILL INTRODUCE BUG. THIS IS TEMPORARY SOLUTION
        //TODO For line above take a look here https://stackoverflow.com/questions/13539050/entitynotfoundexception-in-hibernate-many-to-one-mapping-however-data-exist
        //TODO https://stackoverflow.com/questions/39784344/check-date-between-two-other-dates-spring-data-jpa
        Place place = eventFilter.getPlaceId() != null ? placeRepository.findById(eventFilter.getPlaceId()).orElse(null) : null;
        //TODO Places and hosts filter?
        Example<Event> example = Example.of(eventBuilder.eventExample(eventFilter.getEventName(), place, eventFilter.getEventType()));
        List<Event> events = eventRepository.findAll(example, pageable)
                .stream()
                .filter(event -> eventFilter.getTimeFrom() == null || (event.getEventDateTime() == null || !event.getEventDateTime().isBefore(eventFilter.getTimeFrom())))
                .filter(event -> eventFilter.getTimeTo() == null || (event.getEventDateTime() == null || !event.getEventDateTime().isAfter(eventFilter.getTimeTo())))
                .filter(event -> eventFilter.getPriceFrom() == null || event.getPrices() == null || event.getPrices().isEmpty() || event.getPrices().stream().anyMatch(price -> price >= eventFilter.getPriceFrom()))
                .filter(event -> eventFilter.getPriceTo() == null || event.getPrices() == null || event.getPrices().isEmpty() || event.getPrices().stream().anyMatch(price -> price <= eventFilter.getPriceTo()))
                //TODO This solution with filtering is a little bit hacky since we should somehow filter event in sql query, not here
                .collect(toList());
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
