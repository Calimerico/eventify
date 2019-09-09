package com.eventify.unconfirmedeventsonhost.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.unconfirmedeventsonhost.application.commands.UnconfirmedEventOnHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHostRepository;
import lombok.RequiredArgsConstructor;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class AddUnconfirmedEventOnHost implements CommandHandler<UnconfirmedEventOnHost,Void> {

    private final UnconfirmedEventsOnHostRepository unconfirmedEventsOnHostRepository;
    @Override
    public Void handle(UnconfirmedEventOnHost unconfirmedEventOnHost) {
        UnconfirmedEventsOnHost unconfirmedEventsOnHost = unconfirmedEventsOnHostRepository.findByUserId(unconfirmedEventOnHost.getHostId());
        unconfirmedEventsOnHost.getUnconfirmedEvents().add(unconfirmedEventOnHost.getEventId());
        unconfirmedEventsOnHostRepository.save(unconfirmedEventsOnHost);
        return null;
    }
}
