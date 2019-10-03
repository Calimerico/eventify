package com.eventify.eventsonhost.domain;

import com.eventify.shared.ddd.UUIDAggregate;
import com.eventify.user.domain.UserAccount;

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

    public EventsOnHost(UserAccount userAccount) {
        host = new Host(userAccount);
        setId(host.getId());
    }

    public EventsOnHost(String name) {
        host = new Host(name);
        setId(host.getId());
    }

    private EventsOnHost(Host host, boolean confirmedByDefault, Set<UUID> unconfirmedEvents, Set<UUID> confirmedEvents) {
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

    private EventsOnHost() {
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

    public static class EventsOnHostBuilder {
        private Host host;
        private boolean confirmedByDefault;
        private Set<UUID> unconfirmedEvents;
        private Set<UUID> confirmedEvents;

        EventsOnHostBuilder() {
        }

        public EventsOnHost.EventsOnHostBuilder host(Host host) {
            this.host = host;
            return this;
        }

        public EventsOnHost.EventsOnHostBuilder confirmedByDefault(boolean confirmedByDefault) {
            this.confirmedByDefault = confirmedByDefault;
            return this;
        }

        public EventsOnHost.EventsOnHostBuilder unconfirmedEvents(Set<UUID> unconfirmedEvents) {
            this.unconfirmedEvents = unconfirmedEvents;
            return this;
        }

        public EventsOnHost.EventsOnHostBuilder confirmedEvents(Set<UUID> confirmedEvents) {
            this.confirmedEvents = confirmedEvents;
            return this;
        }

        public EventsOnHost build() {
            return new EventsOnHost(host, confirmedByDefault, unconfirmedEvents, confirmedEvents);
        }
    }
}
