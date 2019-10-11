package com.eventify.eventsonhost.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.eventsonhost.application.commands.ConfirmEventHost;
import com.eventify.eventsonhost.domain.EventsOnHost;
import com.eventify.eventsonhost.domain.EventsOnHostRepository;
import lombok.RequiredArgsConstructor;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class ConfirmEventHostHandler implements CommandHandler<ConfirmEventHost, Void> {

    private final EventsOnHostRepository eventsOnHostRepository;

    @Override
    public Void handle(ConfirmEventHost confirmEventHost) {
        EventsOnHost eventsOnHost = eventsOnHostRepository.findByHostId(confirmEventHost.getHostId());
        eventsOnHost.removeEvent(confirmEventHost.getEventId());
        eventsOnHost.addConfirmedEvent(confirmEventHost.getEventId());
        eventsOnHostRepository.save(eventsOnHost);
        return null;
    }
}
