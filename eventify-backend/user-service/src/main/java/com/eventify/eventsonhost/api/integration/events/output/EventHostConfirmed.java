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
public class EventHostConfirmed implements IntegrationEvent {
    private UUID eventId;
    private UUID hostId;

    public static EventHostConfirmed fromDomain(com.eventify.eventsonhost.domain.events.EventHostConfirmed eventHostConfirmed) {
        return EventHostConfirmed
                .builder()
                .eventId(eventHostConfirmed.getEventId())
                .hostId(eventHostConfirmed.getHostId())
                .build();
    }
}
