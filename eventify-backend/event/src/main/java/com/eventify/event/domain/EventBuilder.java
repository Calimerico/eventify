package com.eventify.event.domain;

import com.eventify.place.domain.Place;
import com.eventify.shared.demo.EventType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.eventify.shared.demo.Util.*;
import static java.util.stream.Collectors.toSet;

@Component
public class EventBuilder {

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


    public EventBuilder eventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public EventBuilder hosts(Set<Host> hosts) {
        this.hosts = emptyIfNull(hosts).stream().map(host -> new HostOnEvent(host,false)).collect(toSet());
        return this;
    }

    public EventBuilder host(Host host) {
        if(this.hosts == null) {
            this.hosts = new HashSet<>();
        }
        this.hosts.add(new HostOnEvent(host,false));
        return this;
    }

    public EventBuilder eventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public EventBuilder place(Place place) {
        this.place = place;
        return this;
    }

    public EventBuilder eventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
        return this;
    }

    public EventBuilder description(String description) {
        this.description = description;
        return this;
    }

    public EventBuilder source(String source) {
        this.source = source;
        return this;
    }

    public EventBuilder profilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public EventBuilder prices(List<Integer> prices) {
        this.prices = prices;
        return this;
    }

    public Event build() {
        return new Event(eventName, hosts, eventType, place, eventDateTime, description, source, profilePicture, prices);
    }

    public Event eventExample(String name, Place place, EventType eventType) {
        return new Event(name,place, eventType);
    }
}
