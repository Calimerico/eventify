package com.eventify.events.api.rest;

import com.eventify.events.domain.Event;
import com.eventify.events.domain.EventType;
import com.eventify.events.domain.Host;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

/**
 * Created by spasoje on 02-Dec-18.
 */
@Builder
@Value
@Relation(value = "resource", collectionRelation = "resources")
public class EventResource extends ResourceSupport {

    @JsonProperty("id")
    private UUID eventId;
    private String eventName;
    private Set<UUID> confirmedHosts;
    private Set<UUID> unconfirmedHosts;
    private EventType eventType;
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
                .confirmedHosts(emptyIfNull(event.getHosts())
                        .stream()
                        .filter(hostOnEvent -> Objects.nonNull(hostOnEvent) && hostOnEvent.isConfirmed())
                        .map(hostOnEvent -> hostOnEvent.getHost().getId())
                        .collect(toSet()))
                .unconfirmedHosts(emptyIfNull(event.getHosts())
                        .stream()
                        .filter(hostOnEvent -> Objects.nonNull(hostOnEvent) && !hostOnEvent.isConfirmed())
                        .map(hostOnEvent -> hostOnEvent.getHost().getId())
                        .collect(toSet()))
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
