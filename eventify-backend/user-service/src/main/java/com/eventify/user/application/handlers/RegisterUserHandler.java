package com.eventify.user.application.handlers;

import com.eventify.user.application.commands.RegisterUser;
import com.eventify.user.domain.UserAccount;
import com.eventify.user.domain.UserBuilders;
import com.eventify.user.infrastructure.UserRepository;
import com.eventify.shared.net.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

/**
 * Created by spasoje on 13-Dec-18.
 */
@CommandHandler
@RequiredArgsConstructor
public class RegisterUserHandler implements com.eventify.shared.demo.CommandHandler<RegisterUser, UUID> {

    private final UserRepository userRepository;

    @Override
    public UUID handle(RegisterUser registerUser) {
        if (userRepository.findByUsername(registerUser.getUsername()).isPresent()) {
            throw new DataIntegrityViolationException("UserAccount already exist");
        }
        UserAccount userAccount = userRepository.save(UserBuilders.aUser()
                .email(registerUser.getEmail())
                .password(registerUser.getPassword())
                .username(registerUser.getUsername())
                .firstName(registerUser.getFirstName())
                .lastName(registerUser.getLastName())
                .sex(registerUser.getSex())
                .role(registerUser.getRole())
                .build()
        );
        return userAccount.getId();
    }
}
