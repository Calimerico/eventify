package com.eventify.place.api.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
class CreatePlaceRequest {
    private double latitude;
    private double longitude;
    @NotNull
    private String name;
    private String city;
}
