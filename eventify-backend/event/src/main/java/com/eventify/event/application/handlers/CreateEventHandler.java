package com.eventify.event.application.handlers;

import com.eventify.event.domain.events.EventAddedEvent;
import com.eventify.event.application.commands.CreateEvent;
import com.eventify.event.domain.Event;
import com.eventify.event.domain.EventBuilder;
import com.eventify.event.infrastructure.EventRepository;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.ddd.DomainEventPublisher;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * Created by spasoje on 15-Dec-18.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class CreateEventHandler implements CommandHandler<CreateEvent, Event> {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final EventBuilder eventBuilder;

    @Override
    public Event handle(CreateEvent createEvent) {
        //TODO First check does event exist with event finder
        Place place = getPlace(createEvent.getPlaceId());
        Event event = eventRepository.save(eventBuilder
                .description(createEvent.getDescription())
                .eventDateTime(createEvent.getEventDateTime())
                .eventName(createEvent.getEventName())
                .eventType(createEvent.getEventType())
                .place(place)
//                .hosts(createEvent.getHosts())//todo
                .source(createEvent.getSource())
                .profilePicture(createEvent.getProfilePicture())
                .prices(createEvent.getPrices())
                .build());
        DomainEventPublisher.publish(EventAddedEvent
                .builder()
                .eventId(event.getId())
                .confirmedHosts(event.findConfirmedHostIds())
                .unconfirmedHosts(event.findUnconfirmedHostIds())
                .build());
        return event;
    }

    private Place getPlace(UUID placeId) {
        Place place = null;
        if (placeId != null) {
            place = placeRepository.findById(placeId).orElse(null);
        }
        return place;
    }
}
