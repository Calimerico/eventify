package com.eventify.place.application.commands;

import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;
import com.eventify.place.domain.Place;

@Value
@Builder
public class CreatePlace implements Command<Place> {
    private double latitude;
    private double longitude;
    private String name;
}
