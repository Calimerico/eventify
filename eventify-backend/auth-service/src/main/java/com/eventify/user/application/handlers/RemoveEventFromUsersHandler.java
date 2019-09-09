package com.eventify.user.application.handlers;

import com.eventify.user.application.commands.RemoveEventFromUsers;
import com.eventify.user.domain.UserAccount;
import com.eventify.user.infrastructure.UserRepository;
import com.eventify.shared.net.CommandHandler;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHostRepository;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@CommandHandler
@RequiredArgsConstructor
public class RemoveEventFromUsersHandler implements com.eventify.shared.demo.CommandHandler<RemoveEventFromUsers, Void> {

    private final UserRepository userRepository;
    private final UnconfirmedEventsOnHostRepository unconfirmedEventsOnHostRepository;

    @Override
    public Void handle(RemoveEventFromUsers removeEventFromUsers) {
        Iterable<UserAccount> users = userRepository.findAllById(removeEventFromUsers.getConfirmedHosts());
        for (UserAccount user : users) {
            user.getEventIdsThatUserOrganize().remove(removeEventFromUsers.getEventId());
            userRepository.save(user);
        }
        Set<UnconfirmedEventsOnHost> unconfirmedEventsOnHosts = unconfirmedEventsOnHostRepository.findByUnconfirmedEventsContains(removeEventFromUsers.getEventId());
        unconfirmedEventsOnHosts.forEach(unconfirmedEventsOnHost -> unconfirmedEventsOnHost.getUnconfirmedEvents().remove(removeEventFromUsers.getEventId()));
        unconfirmedEventsOnHostRepository.saveAll(unconfirmedEventsOnHosts);
        return null;
    }
}
