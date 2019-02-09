package com.eventify.auth.application.handlers;

import com.eventify.auth.application.commands.RegisterUser;
import com.eventify.auth.domain.User;
import com.eventify.auth.domain.UserBuilders;
import com.eventify.auth.infrastructure.UserRepository;
import com.eventify.shared.net.CommandHandler;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;

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
            throw new RuntimeException("Here I should implement 409 status code");//TODO https://stackoverflow.com/questions/3825990/http-response-code-for-post-when-resource-already-exists
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
