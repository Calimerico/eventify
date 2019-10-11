package com.eventify.eventsonhost.domain;

import com.eventify.eventsonhost.domain.events.EventHostConfirmed;
import com.eventify.eventsonhost.domain.events.EventHostUnconfirmed;
import com.eventify.shared.ddd.DomainEventPublisher;
import com.eventify.shared.ddd.UUIDAggregate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class EventsOnHost extends UUIDAggregate {

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Host host;
    private boolean confirmedByDefault = true;
    @ElementCollection
    private Set<UUID> unconfirmedEvents = new HashSet<>();
    @ElementCollection
    private Set<UUID> confirmedEvents = new HashSet<>();

    EventsOnHost(String name) {
        host = new Host(name);
        setId(host.getId());
    }

    private EventsOnHost() {
    }

    EventsOnHost(Host host, boolean confirmedByDefault, Set<UUID> unconfirmedEvents, Set<UUID> confirmedEvents) {
        this.host = host;
        this.confirmedByDefault = confirmedByDefault;
        this.unconfirmedEvents = unconfirmedEvents;
        this.confirmedEvents = confirmedEvents;
        setId(host.getId());
        checkAggregate();
    }

    private void checkAggregate() {
        if (host == null) {
            throw new IllegalStateException("Host must not be null!");
        }
        if (!host.getId().equals(getId())) {
            throw new IllegalStateException("Id not set properly! Host id must be the same as EventsOnHost id!");
        }
    }

    public void addUnconfirmedEvent(UUID eventId) {
        DomainEventPublisher.publish(EventHostUnconfirmed
                .builder()
                .eventId(eventId)
                .hostId(host.getId())
                .build());
        this.unconfirmedEvents.add(eventId);
    }

    public void addConfirmedEvent(UUID eventId) {
        this.confirmedEvents.add(eventId);
        DomainEventPublisher.publish(EventHostConfirmed
                .builder()
                .hostId(host.getId())
                .eventId(eventId)
                .build());
    }

    public void removeEvent(UUID eventId) {
        this.unconfirmedEvents.remove(eventId);
        this.confirmedEvents.remove(eventId);
    }

    public static EventsOnHostBuilder builder() {
        return new EventsOnHostBuilder();
    }

    public boolean isConfirmedByDefault() {
        return this.confirmedByDefault;
    }

    public Set<UUID> getUnconfirmedEvents() {
        return new HashSet<>(this.unconfirmedEvents);
    }

    public Set<UUID> getConfirmedEvents() {
        return new HashSet<>(this.confirmedEvents);
    }

    public void setConfirmedByDefault(boolean confirmedByDefault) {
        this.confirmedByDefault = confirmedByDefault;
    }


}
