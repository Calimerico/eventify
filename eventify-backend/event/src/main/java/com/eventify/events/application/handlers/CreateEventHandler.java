package com.eventify.events.application.handlers;

import com.eventify.events.EventAddedEvent;
import com.eventify.events.application.commands.CreateEvent;
import com.eventify.events.domain.Event;
import com.eventify.events.domain.EventFactory;
import com.eventify.events.domain.Host;
import com.eventify.events.domain.Place;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.events.infrastructure.KafkaEventProducer;
import com.eventify.events.infrastructure.PlaceRepository;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by spasoje on 15-Dec-18.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class CreateEventHandler implements CommandHandler<CreateEvent, Event> {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;//TODO Replace repo with finder
    private final KafkaEventProducer kafkaEventProducer;//TODO This should be part of domain class?

    @Override
    public Event handle(CreateEvent createEvent) {
        //TODO First check does event exist with event finder
        Event event = eventRepository.save(EventFactory
                .aEvent()
                .description(createEvent.getDescription())
                .eventDateTime(createEvent.getEventDateTime())
                .eventName(createEvent.getEventName())
                .eventType(createEvent.getEventType())
                .place(placeRepository.findById(createEvent.getPlaceId()).orElse(new Place(Collections.singletonList("Place"))))//TODO
                .hosts(createEvent.getHosts())
                .source(createEvent.getSource())
                .profilePicture(createEvent.getProfilePicture())
                .prices(createEvent.getPrices())
                .build());
        kafkaEventProducer.send(EventAddedEvent
                .builder()
                .eventId(event.getEventId())
                .hosts(event.getHosts().stream().filter(Objects::nonNull).map(Host::getId).collect(Collectors.toSet()))
                .build());
        return event;
    }
}
