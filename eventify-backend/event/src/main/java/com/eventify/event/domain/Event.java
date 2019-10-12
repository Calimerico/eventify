package com.eventify.event.domain;

/**
 * Created by spasoje on 01-Nov-18.
 */

import com.eventify.place.domain.Place;
import com.eventify.shared.ddd.UUIDAggregate;
import com.eventify.shared.demo.EventType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

/**
 * Created by spasoje on 15-Jun-17.
 */
@Entity
public class Event extends UUIDAggregate {
    private String eventName;

    @ManyToMany
    private Set<HostOnEvent> hosts;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ManyToOne
    private Place place;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    @ElementCollection
    private List<Integer> prices;//TODO Introduce Ticket entity or maybe embeddable?
    private UUID createdBy;
    private LocalDateTime createDateTime;

    Event(String eventName, Set<HostOnEvent> hosts, EventType eventType, Place place, LocalDateTime eventDateTime, String description, String source, String profilePicture, List<Integer> prices, UUID createdBy, LocalDateTime createDateTime) {
        this.eventName = eventName;
        this.hosts = hosts;
        this.eventType = eventType;
        this.place = place;
        this.eventDateTime = eventDateTime;
        this.description = description;
        this.source = source;
        this.profilePicture = profilePicture;
        this.prices = prices;
        this.createdBy = createdBy;
        this.createDateTime = createDateTime;
        checkAggregate();
    }

    //todo THIS CONSTRUCTOR IS USED JUST FOR EVENT EXAMPLE BECAUSE IT PRODUCES INVALID EVENT(no checkAggregate() is called)
    Event(String name, Place place, EventType eventType) {
        this.eventName = name;
        this.place = place;
        this.eventType = eventType;
        setId(null);
    }

    private Event() {
    }

    private void checkAggregate() {
        if (getId() == null || eventName == null || eventType == null || eventDateTime == null) {
            throw new IllegalStateException("Event name, id, type and dateTime must be specified!");
        }
        if (eventDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Event date time must be in the future!");
        }
    }

    public void update(EventUpdateParam param) {
        this.eventName = param.getEventName();
        this.hosts = param.getHosts();
        this.place = param.getPlace();
        this.eventType = param.getEventType();
        this.eventDateTime = param.getEventDateTime();
        this.description = param.getDescription();
        this.source = param.getSource();
        this.profilePicture = param.getProfilePicture();
        this.prices = param.getPrices();
        checkAggregate();
    }

    public void confirmHost(UUID hostId) {
        hosts
                .stream()
                .filter(hostOnEvent -> hostOnEvent.getHost().getId().equals(hostId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Event " + getId() + " does not contain host " + hostId))
                .setConfirmed(true);
    }

    public void unconfirmHost(UUID hostId) {
        hosts
                .stream()
                .filter(hostOnEvent -> hostOnEvent.getHost().getId().equals(hostId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Event " + getId() + " does not contain host " + hostId))
                .setConfirmed(false);
    }

    public Set<HostDto> findConfirmedHosts() {
        return emptyIfNull(hosts).stream()
                .filter(hostOnEvent -> Objects.nonNull(hostOnEvent) && hostOnEvent.isConfirmed())
                .map(hostOnEvent -> new HostDto(hostOnEvent.getId(),hostOnEvent.getHost().getName()))
                .collect(toSet());
    }

    public Set<HostDto> findUnconfirmedHosts() {
        return emptyIfNull(hosts).stream()
                .filter(hostOnEvent -> Objects.nonNull(hostOnEvent) && !hostOnEvent.isConfirmed())
                .map(hostOnEvent -> new HostDto(hostOnEvent.getId(),hostOnEvent.getHost().getName()))
                .collect(toSet());
    }

    public Set<UUID> findConfirmedHostIds() {
        return findConfirmedHosts().stream().map(HostDto::getId).collect(toSet());
    }

    public Set<UUID> findUnconfirmedHostIds() {
        return findUnconfirmedHosts().stream().map(HostDto::getId).collect(toSet());
    }

    public boolean isUserHostForThisEvent(UUID userId) {
        return findConfirmedHostIds().contains(userId);
    }

    public UUID getPlaceId() {
        return place == null ? null : place.getId();
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public List<Integer> getPrices() {
        return prices;
    }

    public EventType getEventType() {
        return eventType;
    }


    public String getDescription() {
        return description;
    }

    public String getSource() {
        return source;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public PlaceDto getPlace() {
        if (place == null)
            return null;
        return new PlaceDto(place.getId(),place.getName(),place.getLatitude(),place.getLongitude());
    }
}

