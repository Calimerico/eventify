package com.eventify.eventsonhost.domain;

import com.eventify.shared.DomainEventPublisher;
import com.eventify.shared.ddd.UUIDAggregate;
import com.eventify.user.domain.UserAccount;
import org.springframework.data.annotation.PersistenceConstructor;

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

    EventsOnHost(String name, DomainEventPublisher domainEventPublisher) {
        super(domainEventPublisher);
        host = new Host(name, domainEventPublisher);
        setId(host.getId());
    }

    EventsOnHost(Host host, boolean confirmedByDefault, Set<UUID> unconfirmedEvents, Set<UUID> confirmedEvents, DomainEventPublisher domainEventPublisher) {
        super(domainEventPublisher);
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
        this.unconfirmedEvents.add(eventId);
    }

    public void addConfirmedEvent(UUID eventId) {
        this.confirmedEvents.add(eventId);
    }

    public void removeEvent(UUID eventId) {
        //todo elementcollection is null, strange behavior
        if (this.unconfirmedEvents == null) {
            this.unconfirmedEvents = new HashSet<>();
        }
        if (this.confirmedEvents == null) {
            this.confirmedEvents = new HashSet<>();
        }
        this.unconfirmedEvents.remove(eventId);
        this.confirmedEvents.remove(eventId);
    }

    @PersistenceConstructor
    private EventsOnHost(DomainEventPublisher domainEventPublisher) {
        super(domainEventPublisher);
    }

    public static EventsOnHostBuilder builder() {
        return new EventsOnHostBuilder();
    }

    public boolean isConfirmedByDefault() {
        return this.confirmedByDefault;
    }

    public Set<UUID> getUnconfirmedEvents() {
        if (this.unconfirmedEvents == null) {
            return new HashSet<>();
        }
        return new HashSet<>(this.unconfirmedEvents);
    }

    public Set<UUID> getConfirmedEvents() {
        if (this.confirmedEvents == null) {
            return new HashSet<>();
        }
        return new HashSet<>(this.confirmedEvents);
    }

    public void setConfirmedByDefault(boolean confirmedByDefault) {
        this.confirmedByDefault = confirmedByDefault;
    }


}
