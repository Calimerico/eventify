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
//todo remove this annotation from ALL domain classes!
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

    private Event(String eventName, Set<HostOnEvent> hosts, EventType eventType, Place place, LocalDateTime eventDateTime, String description, String source, String profilePicture, List<Integer> prices) {
        this.eventName = eventName;
        this.hosts = hosts;
        this.eventType = eventType;
        this.place = place;
        this.eventDateTime = eventDateTime;
        this.description = description;
        this.source = source;
        this.profilePicture = profilePicture;
        this.prices = prices;
        checkAggregate();
    }

    private Event() {
    }

    private void checkAggregate() {
        if (eventName == null || eventType == null || eventDateTime == null) {
            throw new IllegalStateException("Event name, type and dateTime must be specified!");
        }
        if (eventDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Event date time must be in the future!");
        }
    }

    public static EventBuilder builder() {
        return new EventBuilder();
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

    public static Event eventExample(String name, Place place, EventType eventType) {
        Event event = new Event();
        event.update(EventUpdateParam
                .builder()
                .eventName(name)
                .place(place)
                .eventType(eventType)
                .build()
        );
        event.setId(null);
        return event;
    }

    public static class EventBuilder {
        private String eventName;
        private Set<HostOnEvent> hosts;
        private EventType eventType;
        private Place place;
        private LocalDateTime eventDateTime;
        private String description;
        private String source;
        private String profilePicture;
        private List<Integer> prices;

        EventBuilder() {
        }

        public Event.EventBuilder eventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public Event.EventBuilder hosts(Set<Host> hosts) {
            this.hosts = emptyIfNull(hosts).stream().map(host -> new HostOnEvent(host,false)).collect(toSet());
            return this;
        }
        public Event.EventBuilder host(Host host) {
            if(this.hosts == null) {
                this.hosts = new HashSet<>();
            }
            this.hosts.add(new HostOnEvent(host,false));
            return this;
        }

        public Event.EventBuilder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Event.EventBuilder place(Place place) {
            this.place = place;
            return this;
        }

        public Event.EventBuilder eventDateTime(LocalDateTime eventDateTime) {
            this.eventDateTime = eventDateTime;
            return this;
        }

        public Event.EventBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Event.EventBuilder source(String source) {
            this.source = source;
            return this;
        }

        public Event.EventBuilder profilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
            return this;
        }

        public Event.EventBuilder prices(List<Integer> prices) {
            this.prices = prices;
            return this;
        }

        public Event build() {

            return new Event(eventName, hosts, eventType, place, eventDateTime, description, source, profilePicture, prices);
        }
    }
}

