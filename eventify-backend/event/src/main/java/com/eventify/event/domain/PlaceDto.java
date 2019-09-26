package com.eventify.event.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class PlaceDto {
    private UUID id;
    private String name;
    private double latitude;
    private double longitude;
}
