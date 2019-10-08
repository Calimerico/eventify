package com.eventify.event.application.handlers;

import com.eventify.event.EventAddedEvent;
import com.eventify.event.application.commands.CreateEvents;
import com.eventify.event.domain.Event;
import com.eventify.event.domain.EventBuilder;
import com.eventify.event.domain.EventRepository;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.demo.CommandHandler;
import com.eventify.shared.kafka.KafkaEventProducer;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static com.eventify.shared.kafka.Topic.EVENTS_TOPIC;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class CreateEventsHandler implements CommandHandler<CreateEvents, Void> {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;//TODO Replace repo with finder
    private final KafkaEventProducer kafkaEventProducer;
    private final EventBuilder eventBuilder;

    @Override
    public Void handle(CreateEvents createEvents) {
        //TODO First check does event exist with event finder
        List<Event> events = new ArrayList<>();
        createEvents.getEvents().forEach(createEvent -> {
            Place place = getPlace(createEvent.getPlaceId());
            events.add(eventBuilder
                    .description(createEvent.getDescription())
                    .eventDateTime(createEvent.getEventDateTime())
                    .eventName(createEvent.getEventName())
                    .eventType(createEvent.getEventType())
                    .place(place)//TODO
//                .hosts(createEvent.getHosts())//todo
                    .source(createEvent.getSource())
                    .profilePicture(createEvent.getProfilePicture())
                    .prices(createEvent.getPrices())
                    .build());
        });
        eventRepository.saveAll(events);
        events.forEach(event -> {
            kafkaEventProducer.send(EventAddedEvent
                    .builder()
                    .eventId(event.getId())
                    .confirmedHosts(event.findConfirmedHostIds())
                    .unconfirmedHosts(event.findUnconfirmedHostIds())
                    .build(), EVENTS_TOPIC);
        });

        return null;
    }

    private Place getPlace(UUID placeId) {//todo
        Place place = null;
        if (placeId != null) {
            place = placeRepository.findById(placeId).orElse(null);
        }
        return place;
    }
}
