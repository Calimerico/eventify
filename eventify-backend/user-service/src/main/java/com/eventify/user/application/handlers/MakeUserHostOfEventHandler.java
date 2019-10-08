package com.eventify.user.application.handlers;

import com.eventify.eventsonhost.domain.EventsOnHost;
import com.eventify.eventsonhost.domain.EventsOnHostBuilder;
import com.eventify.eventsonhost.domain.EventsOnHostRepository;
import com.eventify.user.application.commands.MakeUserHostOfEvent;
import com.eventify.user.infrastructure.UserRepository;
import com.eventify.shared.net.CommandHandler;
import lombok.RequiredArgsConstructor;

/**
 * Created by spasoje on 20-Dec-18.
 */
@CommandHandler
@RequiredArgsConstructor
public class MakeUserHostOfEventHandler implements com.eventify.shared.demo.CommandHandler<MakeUserHostOfEvent, Void> {
    private final EventsOnHostRepository eventsOnHostRepository;
    private final UserRepository userRepository;
    private final EventsOnHostBuilder eventsOnHostBuilder;

    @Override
    public Void handle(MakeUserHostOfEvent makeUserHostOfEvent) {
        EventsOnHost eventsOnHost = eventsOnHostRepository.findById(makeUserHostOfEvent.getUserId())
                .orElseGet(() -> eventsOnHostBuilder.fromUserAccount(userRepository.loadUser(makeUserHostOfEvent.getUserId())));
        eventsOnHost.removeEvent(makeUserHostOfEvent.getEventId());
        eventsOnHost.addConfirmedEvent(makeUserHostOfEvent.getEventId());
        eventsOnHostRepository.save(eventsOnHost);
        return null;
    }
}
