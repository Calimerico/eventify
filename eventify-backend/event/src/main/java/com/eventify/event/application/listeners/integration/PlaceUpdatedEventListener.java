package com.eventify.event.application.listeners.integration;

import com.eventify.place.domain.events.PlaceUpdatedEvent;
import com.eventify.shared.demo.Gate;
import com.eventify.shared.demo.IntegrationEventListener;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.eventify.shared.kafka.KafkaStreams.PLACES_TOPIC_INPUT_CHANNEL;

@Component
@RequiredArgsConstructor
public class PlaceUpdatedEventListener implements IntegrationEventListener<PlaceUpdatedEvent> {

    private final Gate gate;

    @Override
    @StreamListener(condition = "headers['eventType'] == 'PlaceUpdatedEvent' ", value = PLACES_TOPIC_INPUT_CHANNEL)
    public void handle(@Payload PlaceUpdatedEvent placeUpdatedEvent) {
        gate.dispatch(com.eventify.event.application.commands.PlaceUpdatedEvent
                .builder()
                .id(placeUpdatedEvent.getId())
                .city(placeUpdatedEvent.getCity())
                .latitude(placeUpdatedEvent.getLatitude())
                .longitude(placeUpdatedEvent.getLongitude())
                .name(placeUpdatedEvent.getName())
                .id(placeUpdatedEvent.getId())
                .build()
        );
    }
}
