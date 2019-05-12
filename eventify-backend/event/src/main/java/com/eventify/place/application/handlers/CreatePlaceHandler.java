package com.eventify.place.application.handlers;

import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;
import com.eventify.place.application.commands.CreatePlace;
import com.eventify.place.domain.Place;

import java.util.HashSet;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class CreatePlaceHandler implements CommandHandler<CreatePlace, Place> {

    private final PlaceRepository placeRepository;

    @Override
    public Place handle(CreatePlace createPlace) {
        Place place = new Place();
        HashSet<String> names = new HashSet<>();
        names.add(createPlace.getName());
        place.setNames(names);
        place.setLatitude(createPlace.getLatitude());
        place.setLongitude(createPlace.getLongitude());
        return placeRepository.save(place);
    }
}
