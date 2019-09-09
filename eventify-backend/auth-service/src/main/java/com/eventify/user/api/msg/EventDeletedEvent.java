package com.eventify.user.api.msg;

import com.eventify.shared.demo.DomainEvent;
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
public class EventDeletedEvent implements DomainEvent {
    private UUID eventId;
    private Set<UUID> confirmedHosts;
    private Set<UUID> unconfirmedHosts;
}
