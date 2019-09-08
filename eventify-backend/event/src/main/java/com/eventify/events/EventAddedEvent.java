package com.eventify.events;

import com.eventify.shared.demo.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 20-Dec-18.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventAddedEvent implements DomainEvent {
    private UUID eventId;
    private Set<UUID> confirmedHosts;
    private Set<UUID> unconfirmedHosts;
}
