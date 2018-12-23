package com.eventify.events.application.handlers;

import com.eventify.events.application.commands.UpdateEvent;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;

/**
 * Created by spasoje on 15-Dec-18.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class UpdateEventHandler implements CommandHandler<UpdateEvent, Void> {

    private final EventRepository eventRepository;

    @Override
    public Void handle(UpdateEvent updateEvent) {
        return null;
    }
}
