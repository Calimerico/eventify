package com.eventify.events.application.handlers;

import com.eventify.events.application.commands.DeleteEvent;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;

/**
 * Created by spasoje on 02-Feb-19.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class DeleteEventHandler implements CommandHandler<DeleteEvent,Boolean> {

    private final EventRepository eventRepository;

    @Override
    public Boolean handle(DeleteEvent deleteEvent) {
        return eventRepository.remove(deleteEvent.getId());
    }
}
