package com.eventify.event.api.integration.events.input;

import com.eventify.shared.demo.IntegrationEvent;
import lombok.*;

import java.util.List;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventsScraped implements IntegrationEvent {
    private List<EventScraped> eventsScraped;
}
