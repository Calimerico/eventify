package com.eventify.place.api.rest;

import lombok.Data;

@Data
public class CreatePlaceRequest {
    private double latitude;
    private double longitude;
    private String name;
    private String city;
}
