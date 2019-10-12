package com.eventify.webscraper.application.listeners.domain;

import com.eventify.shared.demo.DomainEventListener;
import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.webscraper.api.integration.events.output.EventScraped;
import com.eventify.webscraper.domain.events.EventsScraped;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.eventify.shared.kafka.Topic.*;
import static java.util.stream.Collectors.*;
import static org.apache.commons.collections4.CollectionUtils.*;

@Component
@RequiredArgsConstructor
public class EventsScrapedListener implements DomainEventListener<EventsScraped> {

    private final KafkaEventProducer kafkaEventProducer;

    @Override
    @TransactionalEventListener
    public void handle(EventsScraped domainEvent) {
        kafkaEventProducer.send(
                com.eventify.webscraper.api.integration.events.output.EventsScraped
                        .builder()
                        .eventsScraped(
                                emptyIfNull(domainEvent.getEventsScraped())
                                        .stream()
                                        .map(EventScraped::fromDomainEvent)
                                        .collect(toList()
                                )
                        )
                        .build(),
                EVENTS_TOPIC);
    }
}
