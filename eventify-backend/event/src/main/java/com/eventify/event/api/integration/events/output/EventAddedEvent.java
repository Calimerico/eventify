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
public class EventAddedEvent implements IntegrationEvent {
    private UUID eventId;
    private Set<UUID> confirmedHosts;
    private Set<UUID> unconfirmedHosts;

    public static EventAddedEvent fromDomainEvent(com.eventify.event.domain.events.EventAddedEvent eventAddedEvent) {
        return builder()
                .eventId(eventAddedEvent.getEventId())
                .confirmedHosts(eventAddedEvent.getConfirmedHosts())
                .unconfirmedHosts(eventAddedEvent.getUnconfirmedHosts())
                .build();
    }
}
