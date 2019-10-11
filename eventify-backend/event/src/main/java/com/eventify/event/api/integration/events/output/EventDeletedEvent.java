package com.eventify.event.api.integration.events.output;

import com.eventify.shared.ddd.DomainEvent;
import com.eventify.shared.demo.IntegrationEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDeletedEvent implements IntegrationEvent {
    private UUID eventId;
    private Set<UUID> hosts;

    public static EventDeletedEvent fromDomainEvent(com.eventify.event.domain.events.EventDeletedEvent eventDeletedEvent) {
        return builder()
                .eventId(eventDeletedEvent.getEventId())
                .hosts(eventDeletedEvent.getHosts())
                .build();
    }
}
