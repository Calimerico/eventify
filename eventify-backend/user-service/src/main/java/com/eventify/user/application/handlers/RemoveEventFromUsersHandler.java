package com.eventify.user.application.handlers;

import com.eventify.user.application.commands.RemoveEventFromUsers;
import com.eventify.shared.net.CommandHandler;
import com.eventify.eventsonhost.domain.EventsOnHost;
import com.eventify.eventsonhost.domain.EventsOnHostRepository;
import lombok.RequiredArgsConstructor;

@CommandHandler
@RequiredArgsConstructor
public class RemoveEventFromUsersHandler implements com.eventify.shared.demo.CommandHandler<RemoveEventFromUsers, Void> {

    private final EventsOnHostRepository eventsOnHostRepository;

    @Override
    public Void handle(RemoveEventFromUsers removeEventFromUsers) {
        Iterable<EventsOnHost> eventsOnHostList = eventsOnHostRepository.findAllById(removeEventFromUsers.getConfirmedHosts());
        for (EventsOnHost eventsOnHost : eventsOnHostList) {
            eventsOnHost.removeEvent(removeEventFromUsers.getEventId());
            eventsOnHostRepository.save(eventsOnHost);
        }
        return null;
    }
}
