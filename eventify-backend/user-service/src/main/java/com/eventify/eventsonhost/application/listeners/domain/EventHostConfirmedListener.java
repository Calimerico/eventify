package com.eventify.eventsonhost.application.listeners.domain;


import com.eventify.eventsonhost.domain.events.EventHostConfirmed;
import com.eventify.shared.demo.DomainEventListener;
import com.eventify.shared.kafka.KafkaEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.eventify.shared.kafka.Topic.*;

@Component
@RequiredArgsConstructor
public class EventHostConfirmedListener implements DomainEventListener<EventHostConfirmed> {

    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public void handle(EventHostConfirmed domainEvent) {
        kafkaEventProducer.send(
                com.eventify.eventsonhost.api.integration.events.output.EventHostConfirmed.fromDomain(domainEvent),
                EVENTS_TOPIC
        );
    }
}