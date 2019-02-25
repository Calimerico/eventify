package com.eventify.events.api.rest;

import com.eventify.events.domain.Event;
import com.eventify.events.domain.Host;
import lombok.Builder;
import lombok.Value;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

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
    private UUID placeId;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    private List<Integer> prices;

    public static EventResource fromEvent(Event event) {
        return EventResource.builder()
                .eventId(event.getEventId())
                .eventName(event.getEventName())
                .hosts(emptyIfNull(event.getHosts()).stream().filter(Objects::nonNull).map(Host::getId).collect(Collectors.toSet()))
                .eventType(event.getEventType())
                .placeId(event.getPlace() != null ? event.getPlace().getId() : null)
                .eventDateTime(event.getEventDateTime())
                .description(event.getDescription())
                .source(event.getSource())
                .profilePicture(event.getProfilePicture())
                .prices(event.getPrices())
                .build();
    }
}
