package com.eventify.event.application.handlers;

import com.eventify.event.EventAddedEvent;
import com.eventify.event.application.commands.CreateEvent;
import com.eventify.event.domain.Event;
import com.eventify.event.infrastructure.EventRepository;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.demo.CommandHandler;
import com.eventify.shared.kafka.KafkaEventProducer;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.UUID;

import static com.eventify.shared.kafka.Topic.EVENTS_TOPIC;

/**
 * Created by spasoje on 15-Dec-18.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class CreateEventHandler implements CommandHandler<CreateEvent, Event> {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;//TODO Replace repo with finder
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public Event handle(CreateEvent createEvent) {
        //TODO First check does event exist with event finder
        Place place = getPlace(createEvent.getPlaceId());
        Event event = eventRepository.save(Event.builder()
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
        kafkaEventProducer.send(EventAddedEvent
                .builder()
                .eventId(event.getId())
                .confirmedHosts(event.findConfirmedHosts())
                .unconfirmedHosts(event.findUnconfirmedHosts())
                .build(), EVENTS_TOPIC);
        return event;
    }

    private Place getPlace(UUID placeId) {//todo
        Place place = null;
        if (placeId != null) {
            Place defaultPlace = Place
                    .builder().build();
            place = placeRepository.findById(placeId).orElse(defaultPlace);
        }
        return place;
    }
}
