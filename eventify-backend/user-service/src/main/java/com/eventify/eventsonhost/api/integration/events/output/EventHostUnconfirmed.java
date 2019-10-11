package com.eventify.eventsonhost.api.integration.events.output;


import com.eventify.shared.demo.IntegrationEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventHostUnconfirmed implements IntegrationEvent {
    private UUID eventId;
    private UUID hostId;

    public static EventHostUnconfirmed fromDomain(com.eventify.eventsonhost.domain.events.EventHostUnconfirmed eventHostUnconfirmed) {
        return EventHostUnconfirmed
                .builder()
                .eventId(eventHostUnconfirmed.getEventId())
                .hostId(eventHostUnconfirmed.getHostId())
                .build();
    }
}
