package com.eventify.event.api.rest;

import com.eventify.event.domain.Event;
import com.eventify.event.domain.EventType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 02-Dec-18.
 */
@Builder
@Value
@Relation(value = "resource", collectionRelation = "resources")
class EventResource extends ResourceSupport {

    @JsonProperty("id")
    private UUID eventId;
    private String eventName;
    private Set<HostResource> hosts;
    private EventType eventType;
    private UUID placeId;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    private List<Integer> prices;

    public static EventResource fromEvent(Event event) {
        Set<HostResource> hosts = new HashSet<>();
        event.findConfirmedHosts().forEach(hostDto -> hosts.add(new HostResource(hostDto.getId(),hostDto.getName(),true)));
        event.findUnconfirmedHosts().forEach(hostDto -> hosts.add(new HostResource(hostDto.getId(),hostDto.getName(),false)));
        return EventResource.builder()
                .eventId(event.getId())
                .eventName(event.getEventName())
                .hosts(hosts)
                .eventType(event.getEventType())
                .placeId(event.getPlaceId())
                .eventDateTime(event.getEventDateTime())
                .description(event.getDescription())
                .source(event.getSource())
                .profilePicture(event.getProfilePicture())
                .prices(event.getPrices())
                .build();
    }
}
