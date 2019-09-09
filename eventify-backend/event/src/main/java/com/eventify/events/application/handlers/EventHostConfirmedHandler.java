package com.eventify.events.application.handlers;

import com.eventify.events.application.commands.EventHostConfirmed;
import com.eventify.events.domain.Event;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class EventHostConfirmedHandler implements CommandHandler<EventHostConfirmed, Void> {

    private final EventRepository eventRepository;

    @Override
    public Void handle(EventHostConfirmed eventHostConfirmed) {
        Event event = eventRepository.loadById(eventHostConfirmed.getEventId());
        event.confirmHost(eventHostConfirmed.getHostId());
        eventRepository.save(event);
        return null;
    }
}
