package com.eventify.eventsonhost.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.eventsonhost.application.commands.AddUnconfirmedEventOnHost;
import com.eventify.eventsonhost.domain.EventsOnHost;
import com.eventify.eventsonhost.domain.EventsOnHostRepository;
import lombok.RequiredArgsConstructor;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class AddUnconfirmedEventOnHostHandler implements CommandHandler<AddUnconfirmedEventOnHost,Void> {

    private final EventsOnHostRepository eventsOnHostRepository;
    @Override
    public Void handle(AddUnconfirmedEventOnHost addUnconfirmedEventOnHost) {
        EventsOnHost eventsOnHost = eventsOnHostRepository.findByHostId(addUnconfirmedEventOnHost.getHostId());
        if (eventsOnHost.isConfirmedByDefault()) {
            eventsOnHost.addConfirmedEvent(addUnconfirmedEventOnHost.getEventId());
        } else {
            eventsOnHost.addUnconfirmedEvent(addUnconfirmedEventOnHost.getEventId());
        }
        eventsOnHostRepository.save(eventsOnHost);
        return null;
    }
}
