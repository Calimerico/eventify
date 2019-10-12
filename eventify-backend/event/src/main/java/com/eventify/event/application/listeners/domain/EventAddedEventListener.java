package com.eventify.event.application.listeners.domain;

import com.eventify.event.domain.events.EventAddedEvent;
import com.eventify.shared.demo.DomainEventListener;
import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.shared.kafka.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.eventify.shared.kafka.Topic.*;

@Component
@RequiredArgsConstructor
public class EventAddedEventListener implements DomainEventListener<EventAddedEvent> {

    private final KafkaEventProducer kafkaEventProducer;

    @Override
    @TransactionalEventListener
    public void handle(EventAddedEvent domainEvent) {
        kafkaEventProducer.send(
                com.eventify.event.api.integration.events.output.EventAddedEvent
                        .fromDomainEvent(domainEvent),
                EVENTS_TOPIC);
    }
}
