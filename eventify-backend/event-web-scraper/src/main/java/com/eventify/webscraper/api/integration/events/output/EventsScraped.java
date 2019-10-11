package com.eventify.webscraper.api.integration.events.output;

import com.eventify.shared.demo.IntegrationEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//TODO Is this appropriate package for output event?Introduce domain and external events?
public class EventsScraped implements IntegrationEvent {
    private List<EventScraped> eventsScraped;
}
