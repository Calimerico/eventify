package com.eventify.auth.application.handlers;

import com.eventify.auth.application.commands.MakeUserHostOfEvent;
import com.eventify.auth.domain.User;
import com.eventify.auth.infrastructure.UserRepository;
import com.eventify.shared.net.CommandHandler;
import lombok.RequiredArgsConstructor;

/**
 * Created by spasoje on 20-Dec-18.
 */
@CommandHandler
@RequiredArgsConstructor
public class MakeUserHostOfEventHandler implements com.eventify.shared.demo.CommandHandler<MakeUserHostOfEvent, Void> {
    private final UserRepository userRepository;

    @Override
    public Void handle(MakeUserHostOfEvent makeUserHostOfEvent) {
        User user = userRepository.findById(makeUserHostOfEvent.getUserId()).orElseThrow(RuntimeException::new);//TODO Runtime or something else?
        user.getEventIdsThatUserOrganize().add(makeUserHostOfEvent.getEventId());//TODO This is not immutable. Change state with getter?
        userRepository.save(user);
        return null;
    }
}
