package com.eventify.auth.application.handlers;

import com.eventify.auth.application.commands.RegisterUser;
import com.eventify.auth.domain.User;
import com.eventify.auth.infrastructure.UserRepository;
import com.eventifyshared.net.CommandHandler;
import lombok.RequiredArgsConstructor;

/**
 * Created by spasoje on 13-Dec-18.
 */
@CommandHandler
@RequiredArgsConstructor
public class RegisterUserHandler implements com.eventifyshared.demo.CommandHandler<RegisterUser,Void> {

    private final UserRepository userRepository;

    @Override
    public Void handle(RegisterUser registerUser) {
        if (userRepository.existsById(registerUser.getUsername())) {
            throw new RuntimeException("Here I should implement 409 status code");//TODO https://stackoverflow.com/questions/3825990/http-response-code-for-post-when-resource-already-exists
        }
        userRepository.save(new User(registerUser.getEmail(),registerUser.getUsername(),registerUser.getPassword(),registerUser.getFirstName(),registerUser.getLastName(),registerUser.getSex(),registerUser.getRole()));
        return null;
    }
}
