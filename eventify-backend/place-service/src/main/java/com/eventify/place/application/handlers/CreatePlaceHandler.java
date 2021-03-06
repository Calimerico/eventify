package com.eventify.place.application.handlers;

import com.eventify.place.domain.events.PlaceUpdatedEvent;
import com.eventify.place.domain.PlaceBuilder;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.ddd.DomainEventPublisher;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;
import com.eventify.place.application.commands.CreatePlace;
import com.eventify.place.domain.Place;

import java.util.HashSet;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class CreatePlaceHandler implements CommandHandler<CreatePlace, Place> {

    private final PlaceRepository placeRepository;
    private final PlaceBuilder placeBuilder;

    @Override
    public Place handle(CreatePlace createPlace) {
        HashSet<String> names = new HashSet<>();
        names.add(createPlace.getName());
        Place place = placeBuilder
                .city(createPlace.getCity())
                .names(names)
                .longitude(createPlace.getLongitude())
                .latitude(createPlace.getLatitude())
                .build();
        Place savedPlace = placeRepository.save(place);
        DomainEventPublisher.publish(PlaceUpdatedEvent
                .builder()
                .city(savedPlace.getCity())
                .id(savedPlace.getId())
                .latitude(savedPlace.getLatitude())
                .longitude(savedPlace.getLongitude())
                .name(savedPlace.getNames().stream().findFirst().orElseThrow(RuntimeException::new))//todo
                .build());
        return savedPlace;
    }
}
