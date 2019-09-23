package com.eventify.event.application.commands;

import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class PlaceUpdatedEvent implements Command<Void> {
    private UUID id;
    private String name;
    private String city;
    private double latitude;
    private double longitude;
}
