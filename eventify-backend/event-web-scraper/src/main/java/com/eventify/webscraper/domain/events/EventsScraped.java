package com.eventify.webscraper.domain.events;

import com.eventify.shared.ddd.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//TODO Is this appropriate package for output event?Introduce domain and external events?
public class EventsScraped implements DomainEvent {
    private List<EventScraped> eventsScraped;
}
