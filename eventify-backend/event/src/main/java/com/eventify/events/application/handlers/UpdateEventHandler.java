package com.eventify.events.application.handlers;

import com.eventify.events.application.commands.UpdateEvent;
import com.eventify.events.domain.Event;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
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
        if (updateEvent.getPlaceId() != null) {
            Place place = placeRepository.findById(updateEvent.getPlaceId())
                    .orElseThrow(() -> new NoSuchElementException("Place with id " + updateEvent.getPlaceId() + " does not exist!"));
            event.setPlace(place);
        }
        event.setEventName(updateEvent.getEventName());
        event.setEventType(updateEvent.getEventType());//TODO Should we allow type to be updated? In domain model we don't allow this
        event.setEventDateTime(updateEvent.getEventDateTime());
        event.setDescription(updateEvent.getDescription());
        event.setSource(updateEvent.getSource());
        event.setProfilePicture(updateEvent.getProfilePicture());
        event.setPrices(updateEvent.getPrices());
        return eventRepository.save(event);
    }
}
