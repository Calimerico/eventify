package com.eventify.eventsonhost.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.eventsonhost.application.commands.DefaultEventHostConfirmed;
import com.eventify.eventsonhost.domain.EventsOnHost;
import com.eventify.eventsonhost.domain.EventsOnHostRepository;
import lombok.RequiredArgsConstructor;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class DefaultEventHostConfirmedHandler implements CommandHandler<DefaultEventHostConfirmed, Void> {

    private final EventsOnHostRepository eventsOnHostRepository;

    @Override
    public Void handle(DefaultEventHostConfirmed defaultEventHostConfirmed) {
        EventsOnHost eventsOnHost = eventsOnHostRepository.findByHostId(defaultEventHostConfirmed.getUserId());
        eventsOnHost.setConfirmedByDefault(defaultEventHostConfirmed.isConfirmedByDefault());
        eventsOnHostRepository.save(eventsOnHost);
        return null;
    }
}
