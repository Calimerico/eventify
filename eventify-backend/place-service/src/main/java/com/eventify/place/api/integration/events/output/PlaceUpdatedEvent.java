package com.eventify.place.api.integration.events.output;

import com.eventify.shared.demo.IntegrationEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceUpdatedEvent implements IntegrationEvent {
    private UUID id;
    private String name;
    private String city;
    private double latitude;
    private double longitude;

    public static PlaceUpdatedEvent fromDomainEvent(com.eventify.place.domain.events.PlaceUpdatedEvent placeUpdatedEvent) {
        return PlaceUpdatedEvent
                .builder()
                .city(placeUpdatedEvent.getCity())
                .name(placeUpdatedEvent.getName())
                .latitude(placeUpdatedEvent.getLatitude())
                .longitude(placeUpdatedEvent.getLongitude())
                .build();
    }
}
