package com.eventify.unconfirmedeventsonhost.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.unconfirmedeventsonhost.application.commands.ConfirmEventHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHostRepository;
import lombok.RequiredArgsConstructor;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class ConfirmEventHostHandler implements CommandHandler<ConfirmEventHost, Void> {

    private final UnconfirmedEventsOnHostRepository unconfirmedEventsOnHostRepository;

    @Override
    public Void handle(ConfirmEventHost confirmEventHost) {
        UnconfirmedEventsOnHost byUserId = unconfirmedEventsOnHostRepository.findByUserId(confirmEventHost.getHostId());
        byUserId.getUnconfirmedEvents().remove(confirmEventHost.getEventId());
        unconfirmedEventsOnHostRepository.save(byUserId);
        return null;
    }
}
