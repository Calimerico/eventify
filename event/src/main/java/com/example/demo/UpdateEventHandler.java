package com.example.demo;

import lombok.RequiredArgsConstructor;

/**
 * Created by spasoje on 15-Dec-18.
 */
@com.net.CommandHandler
@RequiredArgsConstructor
public class UpdateEventHandler implements CommandHandler<UpdateEvent, Void> {

    private final EventRepository eventRepository;

    @Override
    public Void handle(UpdateEvent updateEvent) {
        return null;
    }
}
