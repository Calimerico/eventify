package com.eventify.event.application.handlers;

import com.eventify.event.application.commands.UpdateEvent;
import com.eventify.event.domain.Event;
import com.eventify.event.domain.EventUpdateParam;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.event.infrastructure.EventRepository;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

/**
 * Created by spasoje on 15-Dec-18.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class UpdateEventHandler implements CommandHandler<UpdateEvent, Event> {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;

    @Override
    public Event handle(UpdateEvent updateEvent) {
        Event event = eventRepository.loadById(updateEvent.getId());
        Place place = null;
        if (updateEvent.getPlaceId() != null) {
            place = placeRepository.findById(updateEvent.getPlaceId())
                    .orElseThrow(() -> new NoSuchElementException("Place with id " + updateEvent.getPlaceId() + " does not exist!"));
        }
        event.update(EventUpdateParam
                .builder()
                .eventName(updateEvent.getEventName())
                .eventType(updateEvent.getEventType())
                .eventDateTime(updateEvent.getEventDateTime())
                .description(updateEvent.getDescription())
                .source(updateEvent.getSource())
                .profilePicture(updateEvent.getProfilePicture())
                .prices(updateEvent.getPrices())
                .place(place)
//                .hosts(updateEvent.geHo)//todo
                .build());
        return eventRepository.save(event);
    }
}
