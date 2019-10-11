package com.eventify.eventsonhost.application.handlers;

import com.eventify.eventsonhost.application.commands.UnconfirmEventHost;
import com.eventify.eventsonhost.domain.EventsOnHost;
import com.eventify.eventsonhost.domain.EventsOnHostRepository;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;


@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class UnconfirmEventHostHandler implements CommandHandler<UnconfirmEventHost, Void> {

    private final EventsOnHostRepository eventsOnHostRepository;

    @Override
    public Void handle(UnconfirmEventHost unconfirmEventHost) {
        EventsOnHost eventsOnHost = eventsOnHostRepository.findByHostId(unconfirmEventHost.getHostId());
        eventsOnHost.removeEvent(unconfirmEventHost.getEventId());
        eventsOnHost.addUnconfirmedEvent(unconfirmEventHost.getEventId());
        eventsOnHostRepository.save(eventsOnHost);
        return null;
    }
}
