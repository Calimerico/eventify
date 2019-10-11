package com.eventify.place.domain.events;

import com.eventify.shared.ddd.DomainEvent;
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
