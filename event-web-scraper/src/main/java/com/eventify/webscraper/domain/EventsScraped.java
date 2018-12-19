package com.eventify.webscraper.domain;

import com.eventifyshared.demo.DomainEvent;
import lombok.Builder;

import java.util.List;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Builder
public class EventsScraped implements DomainEvent {
    private final List<EventScraped> eventsScraped;
}
