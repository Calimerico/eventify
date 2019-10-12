package com.eventify.event.api.integration.events.input;

import com.eventify.shared.ddd.DomainEvent;
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
}
