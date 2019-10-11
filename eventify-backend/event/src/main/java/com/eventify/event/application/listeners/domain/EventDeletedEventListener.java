package com.eventify.event.application.listeners.domain;

import com.eventify.event.domain.events.EventDeletedEvent;
import com.eventify.shared.demo.DomainEventListener;
import com.eventify.shared.kafka.KafkaEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.eventify.shared.kafka.Topic.*;

@Component
@RequiredArgsConstructor
public class EventDeletedEventListener implements DomainEventListener<EventDeletedEvent> {

    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public void handle(EventDeletedEvent domainEvent) {
        kafkaEventProducer.send(
                com.eventify.event.api.integration.events.output.EventDeletedEvent
                        .fromDomainEvent(domainEvent),
                EVENTS_TOPIC
        );
    }
}
