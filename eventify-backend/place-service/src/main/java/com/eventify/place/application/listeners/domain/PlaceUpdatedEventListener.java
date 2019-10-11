package com.eventify.place.application.listeners.domain;

import com.eventify.place.domain.events.PlaceUpdatedEvent;
import com.eventify.shared.demo.DomainEventListener;
import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.shared.kafka.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.eventify.shared.kafka.Topic.*;

@Component
@RequiredArgsConstructor
public class PlaceUpdatedEventListener implements DomainEventListener<PlaceUpdatedEvent> {

    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public void handle(PlaceUpdatedEvent domainEvent) {
        kafkaEventProducer.send(
                com.eventify.place.api.integration.events.output.PlaceUpdatedEvent.fromDomainEvent(domainEvent),
                PLACES_TOPIC
        );
    }
}
