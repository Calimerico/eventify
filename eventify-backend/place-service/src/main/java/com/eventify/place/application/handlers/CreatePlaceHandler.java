package com.eventify.place.application.handlers;

import com.eventify.place.PlaceUpdatedEvent;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.demo.CommandHandler;
import com.eventify.shared.kafka.KafkaEventProducer;
import lombok.RequiredArgsConstructor;
import com.eventify.place.application.commands.CreatePlace;
import com.eventify.place.domain.Place;

import java.util.HashSet;

import static com.eventify.shared.kafka.Topic.EVENTS_TOPIC;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class CreatePlaceHandler implements CommandHandler<CreatePlace, Place> {

    private final PlaceRepository placeRepository;
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public Place handle(CreatePlace createPlace) {
        Place place = new Place();
        HashSet<String> names = new HashSet<>();
        names.add(createPlace.getName());
        place.setCity(createPlace.getCity());
        place.setNames(names);
        place.setLatitude(createPlace.getLatitude());
        place.setLongitude(createPlace.getLongitude());
        Place savedPlace = placeRepository.save(place);
        kafkaEventProducer.send(PlaceUpdatedEvent
                .builder()
                .city(savedPlace.getCity())
                .id(savedPlace.getId())
                .latitude(savedPlace.getLatitude())
                .longitude(savedPlace.getLongitude())
                .name(savedPlace.getNames().stream().findFirst().orElseThrow(RuntimeException::new))//todo
                        .build(),
                EVENTS_TOPIC
        );
        return savedPlace;
    }
}
