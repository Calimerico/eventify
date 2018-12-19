package com.example.demo;

import lombok.RequiredArgsConstructor;

/**
 * Created by spasoje on 15-Dec-18.
 */
@com.net.CommandHandler
@RequiredArgsConstructor
public class CreateEventHandler implements CommandHandler<CreateEvent, Void> {

    private final EventRepository eventRepository;

    @Override
    public Void handle(CreateEvent createEvent) {
        return null;
    }
}
