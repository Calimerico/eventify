package com.eventify.place.api.msg;

import com.eventify.shared.demo.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceUpdatedEvent implements DomainEvent {
    private UUID id;
    private String name;
    private String city;
    private double latitude;
    private double longitude;
}