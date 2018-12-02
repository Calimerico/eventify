package com;

import com.example.demo.DomainEvent;
import lombok.Builder;

import java.util.List;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Builder
public class EventsScraped implements DomainEvent {
    private final List<EventScraped> eventsScraped;
}
