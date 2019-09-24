package com.eventify.place.application.handlers;

import com.eventify.event.application.commands.PlaceUpdatedEvent;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.net.CommandHandler;
import lombok.RequiredArgsConstructor;

@CommandHandler
@RequiredArgsConstructor
public class PlaceUpdatedEventHandler implements com.eventify.shared.demo.CommandHandler<PlaceUpdatedEvent, Void> {

    private final PlaceRepository placeRepository;

    @Override
    public Void handle(PlaceUpdatedEvent placeUpdatedEvent) {
        Place place = placeRepository.findById(placeUpdatedEvent.getId())
                .orElse(new Place(
                        placeUpdatedEvent.getId(),
                        placeUpdatedEvent.getName(),
                        placeUpdatedEvent.getCity(),
                        placeUpdatedEvent.getLatitude(),
                        placeUpdatedEvent.getLongitude()
                        )
                );
        placeRepository.save(place);
        return null;
    }
}
