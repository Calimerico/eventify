package com.eventify.place.api.rest;

import com.eventify.place.domain.Place;
import lombok.Builder;
import lombok.Value;
import org.springframework.hateoas.core.Relation;

import java.util.Set;
import java.util.UUID;

@Builder
@Value
@Relation(value = "resource", collectionRelation = "resources")
class PlaceResource {
    private UUID id;
    private double latitude;
    private double longitude;
    private Set<String> names;

    static PlaceResource fromPlace(Place createdPlace) {
        return new PlaceResource(createdPlace.getId(), createdPlace.getLatitude(), createdPlace.getLongitude(), createdPlace.getNames());
    }
}
