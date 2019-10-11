package com.eventify.place.domain;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlaceBuilder {

    private UUID id;
    private String name;
    private String city;
    private double latitude;
    private double longitude;

    public PlaceBuilder() {
    }

    public PlaceBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public PlaceBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PlaceBuilder city(String city) {
        this.city = city;
        return this;
    }

    public PlaceBuilder latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public PlaceBuilder longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Place build() {
        return new Place(id, name, city, latitude, longitude);
    }
}
