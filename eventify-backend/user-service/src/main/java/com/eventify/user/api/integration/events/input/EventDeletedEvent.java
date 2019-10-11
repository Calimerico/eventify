package com.eventify.user.api.integration.events.input;

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
    private Set<UUID> confirmedHosts;
    private Set<UUID> unconfirmedHosts;
}
