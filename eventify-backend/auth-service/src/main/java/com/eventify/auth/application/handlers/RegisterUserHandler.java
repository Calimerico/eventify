package com.eventify.auth.application.handlers;

import com.eventify.auth.application.commands.RegisterUser;
import com.eventify.auth.domain.UserBuilders;
import com.eventify.auth.infrastructure.UserRepository;
import com.eventify.shared.net.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Created by spasoje on 13-Dec-18.
 */
@CommandHandler
@RequiredArgsConstructor
public class RegisterUserHandler implements com.eventify.shared.demo.CommandHandler<RegisterUser,Void> {

    private final UserRepository userRepository;

    @Override
    public Void handle(RegisterUser registerUser) {
        if (userRepository.findByUsername(registerUser.getUsername()).isPresent()) {
            throw new DataIntegrityViolationException("UserAccount already exist");
        }
        userRepository.save(UserBuilders.aUser()
                .email(registerUser.getEmail())
                .password(registerUser.getPassword())
                .username(registerUser.getUsername())
                .firstName(registerUser.getFirstName())
                .lastName(registerUser.getLastName())
                .sex(registerUser.getSex())
                .build()
        );
        return null;
    }
}
