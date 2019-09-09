package com.eventify.user.application.handlers;

import com.eventify.user.application.commands.MakeUserHostOfEvent;
import com.eventify.user.domain.UserAccount;
import com.eventify.user.infrastructure.UserRepository;
import com.eventify.shared.net.CommandHandler;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

/**
 * Created by spasoje on 20-Dec-18.
 */
@CommandHandler
@RequiredArgsConstructor
public class MakeUserHostOfEventHandler implements com.eventify.shared.demo.CommandHandler<MakeUserHostOfEvent, Void> {
    private final UserRepository userRepository;

    @Override
    public Void handle(MakeUserHostOfEvent makeUserHostOfEvent) {
        UserAccount user = userRepository.findById(makeUserHostOfEvent.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User with id " + makeUserHostOfEvent.getUserId() + " does not exist"));
        user.getEventIdsThatUserOrganize().add(makeUserHostOfEvent.getEventId());//TODO This is not immutable. Change state with getter?
        userRepository.save(user);
        return null;
    }
}