package com.eventify.place.api.rest;

import com.eventify.place.application.commands.CreatePlace;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.demo.Gate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.eventify.place.api.rest.PlaceResource.fromPlace;
import static com.eventify.shared.security.RoleName.ROLE_ADMIN;

@RestController
@RequestMapping(value = PlaceController.BASE_PATH)
@RequiredArgsConstructor
public class PlaceController {

    public static final String BASE_PATH = "/places";
    private final Gate gate;
    private final PlaceRepository placeRepository;//todo finder here needed

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<PlaceResource> insertPlace(@RequestBody @Valid CreatePlaceRequest createPlaceRequest) {
        Place createdPlace = gate.dispatch(CreatePlace
                .builder()
                .name(createPlaceRequest.getName())
                .city(createPlaceRequest.getCity())
                .latitude(createPlaceRequest.getLatitude())
                .longitude(createPlaceRequest.getLongitude())
                .build());
        return ResponseEntity.ok(fromPlace(createdPlace));
    }

    @GetMapping
    public ResponseEntity<List<PlaceResource>> getPlaces(@RequestParam String city) {
        return ResponseEntity.ok(placeRepository.findByCity(city)
                .stream()
                .map(PlaceResource::fromPlace).collect(Collectors.toList())
        );
    }
}
