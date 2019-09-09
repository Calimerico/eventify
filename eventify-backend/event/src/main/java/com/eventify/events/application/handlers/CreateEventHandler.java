package com.eventify.events.application.handlers;

import com.eventify.events.EventAddedEvent;
import com.eventify.events.application.commands.CreateEvent;
import com.eventify.events.domain.Event;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.demo.CommandHandler;
import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.shared.kafka.Topic;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.eventify.shared.kafka.Topic.EVENTS_TOPIC;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

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
                .eventId(event.getEventId())
                .confirmedHosts(emptyIfNull(event.getHosts())
                        .stream()
                        .filter(hostOnEvent -> Objects.nonNull(hostOnEvent) && hostOnEvent.isConfirmed())
                        .map(hostOnEvent -> hostOnEvent.getHost().getId())
                        .collect(Collectors.toSet()))
                .unconfirmedHosts(emptyIfNull(event.getHosts())
                        .stream()
                        .filter(hostOnEvent -> Objects.nonNull(hostOnEvent) && !hostOnEvent.isConfirmed())
                        .map(hostOnEvent -> hostOnEvent.getHost().getId())
                        .collect(Collectors.toSet()))
                .build(), EVENTS_TOPIC);
        return event;
    }

    private Place getPlace(UUID placeId) {//todo
        Place place = null;
        if (placeId != null) {
            Place defaultPlace = new Place();
            HashSet<String> names = new HashSet<>();
            names.add("Place");
            defaultPlace.setNames(names);
            place = placeRepository.findById(placeId).orElse(defaultPlace);
        }
        return place;
    }
}
