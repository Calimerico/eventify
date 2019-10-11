package com.eventify.eventsonhost.application.listeners.domain;

import com.eventify.eventsonhost.domain.events.EventHostUnconfirmed;
import com.eventify.shared.demo.DomainEventListener;
import com.eventify.shared.kafka.KafkaEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.eventify.shared.kafka.Topic.*;

@Component
@RequiredArgsConstructor
public class EventHostUnconfirmedListener implements DomainEventListener<EventHostUnconfirmed> {

    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public void handle(EventHostUnconfirmed domainEvent) {
        kafkaEventProducer.send(
                com.eventify.eventsonhost.api.integration.events.output.EventHostUnconfirmed.fromDomain(domainEvent),
                EVENTS_TOPIC
        );
    }
}
