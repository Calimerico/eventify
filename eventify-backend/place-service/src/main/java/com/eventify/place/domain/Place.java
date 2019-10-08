package com.eventify.place.domain;

import com.eventify.shared.DomainEventPublisher;
import com.eventify.shared.ddd.UUIDAggregate;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Place extends UUIDAggregate {

    private double latitude;
    private double longitude;
    @ElementCollection
    private Set<String> names;
    private String city;

    Place(double latitude, double longitude, Set<String> names, String city, DomainEventPublisher domainEventPublisher) {
        super(domainEventPublisher);
        this.latitude = latitude;
        this.longitude = longitude;
        this.names = names;
        this.city = city;
    }

    @PersistenceConstructor
    private Place(DomainEventPublisher domainEventPublisher) {
        super(domainEventPublisher);
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Set<String> getNames() {
        return new HashSet<>(this.names);
    }

    public String getCity() {
        return city;
    }

}
