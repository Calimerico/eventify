package com.eventify.events.application.handlers;

import com.eventify.events.EventAddedEvent;
import com.eventify.events.application.commands.CreateEvent;
import com.eventify.events.domain.Event;
import com.eventify.events.domain.EventFactory;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.events.infrastructure.KafkaEventProducer;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;

/**
 * Created by spasoje on 15-Dec-18.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class CreateEventHandler implements CommandHandler<CreateEvent, Void> {

    private final EventRepository eventRepository;
    private final KafkaEventProducer kafkaEventProducer;//TODO This should be part of domain class?

    @Override
    public Void handle(CreateEvent createEvent) {
        //TODO First check does event exist with event finder
        Event event = eventRepository.save(EventFactory
                .aEvent()
                .description(createEvent.getDescription())
                .eventDateAndTime(createEvent.getEventDateAndTime())
                .eventName(createEvent.getEventName())
                .eventType(createEvent.getEventType())
                .placeId(createEvent.getPlaceId())
                .hosts(createEvent.getHosts())
                .source(createEvent.getSource())
                .build());
        kafkaEventProducer.send(EventAddedEvent
                .builder()
                .eventId(event.getEventId())
                .hosts(event.getHosts())
                .build());
        return null;
    }
}
