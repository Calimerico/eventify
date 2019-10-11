package com.eventify.event.domain.events;

import com.eventify.shared.ddd.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventHostConfirmed implements DomainEvent {
    private UUID eventId;
    private UUID hostId;
}
