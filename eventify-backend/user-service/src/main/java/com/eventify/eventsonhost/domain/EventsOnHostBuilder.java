package com.eventify.eventsonhost.domain;

import com.eventify.user.domain.UserAccount;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
public class EventsOnHostBuilder {

    private Host host;
    private boolean confirmedByDefault;
    private Set<UUID> unconfirmedEvents;
    private Set<UUID> confirmedEvents;

    EventsOnHostBuilder() {
    }

    public EventsOnHostBuilder host(Host host) {
        this.host = host;
        return this;
    }

    public EventsOnHostBuilder confirmedByDefault(boolean confirmedByDefault) {
        this.confirmedByDefault = confirmedByDefault;
        return this;
    }

    public EventsOnHostBuilder unconfirmedEvents(Set<UUID> unconfirmedEvents) {
        this.unconfirmedEvents = unconfirmedEvents;
        return this;
    }

    public EventsOnHostBuilder confirmedEvents(Set<UUID> confirmedEvents) {
        this.confirmedEvents = confirmedEvents;
        return this;
    }

    public EventsOnHost build() {
        return new EventsOnHost(host, confirmedByDefault, unconfirmedEvents, confirmedEvents);
    }

    public EventsOnHost fromUserAccount(UserAccount user) {
        Host host = new Host(user);
        return this
                .host(host)
                .build();
    }

    public EventsOnHost fromName(String name) {
        Host host = new Host(name);
        return this.host(host).build();
    }
}
