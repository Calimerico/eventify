package com.eventify.place.domain;

import com.eventify.shared.ddd.UUIDAggregate;

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

    Place(double latitude, double longitude, Set<String> names, String city) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.names = names;
        this.city = city;
    }

    private Place() {
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
