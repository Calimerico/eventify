package com.eventify.unconfirmedeventsonhost.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.unconfirmedeventsonhost.application.commands.DefaultEventHostConfirmed;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHostRepository;
import lombok.RequiredArgsConstructor;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class DefaultEventHostConfirmedHandler implements CommandHandler<DefaultEventHostConfirmed, Void> {

    private final UnconfirmedEventsOnHostRepository unconfirmedEventsOnHostRepository;

    @Override
    public Void handle(DefaultEventHostConfirmed defaultEventHostConfirmed) {
        UnconfirmedEventsOnHost unconfirmedEventsOnHost = unconfirmedEventsOnHostRepository.findByUserId(defaultEventHostConfirmed.getUserId());
        unconfirmedEventsOnHost.setConfirmedByDefault(defaultEventHostConfirmed.isConfirmedByDefault());
        unconfirmedEventsOnHostRepository.save(unconfirmedEventsOnHost);
        return null;
    }
}
