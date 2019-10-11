package com.eventify.place.domain;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PlaceBuilder {
    private double latitude;
    private double longitude;
    private Set<String> names;
    private String city;

    PlaceBuilder() {
    }

    public PlaceBuilder latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public PlaceBuilder longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public PlaceBuilder names(Set<String> names) {
        this.names = names;
        return this;
    }

    public PlaceBuilder city(String city) {
        this.city = city;
        return this;
    }

    public Place build() {
        return new Place(latitude, longitude, names, city);
    }

}
