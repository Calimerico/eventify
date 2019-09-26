package com.eventify.event.api.rest;

import lombok.Data;

import java.util.UUID;

@Data
public class PlaceResource {
    private UUID id;
    private String name;
    private double latitude;
    private double longitude;
}
