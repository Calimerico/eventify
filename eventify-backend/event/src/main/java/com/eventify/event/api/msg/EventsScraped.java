package com.eventify.event.api.msg;

import com.eventify.shared.demo.DomainEvent;
import lombok.*;

import java.util.List;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventsScraped implements DomainEvent {
    private List<EventScraped> eventsScraped;
}
