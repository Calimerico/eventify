package com.eventify.auth.application.handlers;

import com.eventify.auth.application.commands.RemoveEventFromUsers;
import com.eventify.auth.domain.UserAccount;
import com.eventify.auth.infrastructure.UserRepository;
import com.eventify.shared.net.CommandHandler;
import lombok.RequiredArgsConstructor;

@CommandHandler
@RequiredArgsConstructor
public class RemoveEventFromUsersHandler implements com.eventify.shared.demo.CommandHandler<RemoveEventFromUsers, Void> {

    private final UserRepository userRepository;

    @Override
    public Void handle(RemoveEventFromUsers removeEventFromUsers) {
        Iterable<UserAccount> users = userRepository.findAllById(removeEventFromUsers.getHosts());
        for (UserAccount user : users) {
            user.getEventIdsThatUserOrganize().remove(removeEventFromUsers.getEventId());
            userRepository.save(user);
        }
        return null;
    }
}
