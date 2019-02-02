package com.eventify.events.api.rest;

import com.eventify.events.domain.Event;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 02-Dec-18.
 */
@Builder
@Value
public class EventResource {
    private UUID eventId;
    private String eventName;
    private Set<UUID> hosts;
    private String eventType;
    private String placeId;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;

    public static EventResource fromEvent(Event event) {
        return EventResource.builder()
                .eventId(event.getEventId())
                .eventName(event.getEventName())
                .hosts(event.getHosts())
                .eventType(event.getEventType())
                .placeId(event.getPlaceId())
                .eventDateTime(event.getEventDateTime())
                .description(event.getDescription())
                .source(event.getSource())
                .profilePicture(event.getProfilePicture())
                .build();
    }
}
