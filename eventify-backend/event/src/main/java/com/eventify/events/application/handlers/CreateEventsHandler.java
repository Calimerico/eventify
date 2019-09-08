package com.eventify.events.application.handlers;

import com.eventify.events.EventAddedEvent;
import com.eventify.events.application.commands.CreateEvents;
import com.eventify.events.domain.Event;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.events.infrastructure.KafkaEventProducer;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class CreateEventsHandler implements CommandHandler<CreateEvents, Void> {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;//TODO Replace repo with finder
    private final KafkaEventProducer kafkaEventProducer;//TODO This should be part of domain class?

    @Override
    public Void handle(CreateEvents createEvents) {
        //TODO First check does event exist with event finder
        List<Event> events = new ArrayList<>();
        createEvents.getEvents().forEach(createEvent -> {
            Place place = getPlace(createEvent.getPlaceId());
            events.add(Event.builder()
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
                    .build());
        });

        return null;
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
