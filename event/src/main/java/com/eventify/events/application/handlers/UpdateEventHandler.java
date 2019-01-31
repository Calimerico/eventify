package com.eventify.events.application.handlers;

import com.eventify.events.application.commands.UpdateEvent;
import com.eventify.events.domain.Event;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by spasoje on 15-Dec-18.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class UpdateEventHandler implements CommandHandler<UpdateEvent, Event> {

    private final EventRepository eventRepository;

    @Override
    public Event handle(UpdateEvent updateEvent) {
        Event event = eventRepository.findByEventId(updateEvent.getId().toString());//TODO
        event.setEventName(updateEvent.getEventName());
        event.setEventType(updateEvent.getEventType());//TODO Should we allow type to be updated? In domain model we don't allow this
        event.setPlaceId(updateEvent.getPlaceId());
        event.setEventDateAndTime(updateEvent.getEventDateAndTime());
        event.setDescription(updateEvent.getDescription());
        event.setSource(updateEvent.getSource());
        return eventRepository.save(event);
    }
}
