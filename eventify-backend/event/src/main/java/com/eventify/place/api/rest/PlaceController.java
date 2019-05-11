package com.eventify.place.api.rest;

import com.eventify.place.application.commands.CreatePlace;
import com.eventify.place.domain.Place;
import com.eventify.shared.demo.Gate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.eventify.place.api.rest.PlaceResource.fromPlace;

@RestController
@RequestMapping(value = "/places")
@RequiredArgsConstructor
public class PlaceController {

    private final Gate gate;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaceResource> insertPlace(@RequestBody CreatePlaceRequest createPlaceRequest) {
        Place createdPlace = gate.dispatch(CreatePlace
                .builder()
                .name(createPlaceRequest.getName())
                .latitude(createPlaceRequest.getLatitude())
                .longitude(createPlaceRequest.getLongitude())
                .build());
        return ResponseEntity.ok(fromPlace(createdPlace));
    }
}
