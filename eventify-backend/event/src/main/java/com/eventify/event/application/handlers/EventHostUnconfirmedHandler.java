package com.eventify.event.application.handlers;

import com.eventify.event.application.commands.EventHostUnconfirmed;
import com.eventify.event.domain.Event;
import com.eventify.event.infrastructure.EventRepository;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class EventHostUnconfirmedHandler implements CommandHandler<EventHostUnconfirmed, Void> {

    private final EventRepository eventRepository;

    @Override
    public Void handle(EventHostUnconfirmed eventHostUnconfirmed) {
        Event event = eventRepository.loadById(eventHostUnconfirmed.getEventId());
        event.unconfirmHost(eventHostUnconfirmed.getHostId());
        eventRepository.save(event);
        return null;
    }
}
