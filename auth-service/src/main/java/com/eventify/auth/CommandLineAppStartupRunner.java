package com.eventify.auth;

import com.eventify.auth.api.rest.RegisterController;
import com.eventify.auth.api.rest.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by spasoje on 15-Dec-18.
 */
@Component
@RequiredArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final RegisterController registerController;

    @Override
    public void run(String... strings) throws Exception {
        registerController.registerUser(RegisterUserRequest
                .builder()
                .userName("admin")
                .firstName("Spasoje")
                .lastName("Petronijevic")
                .email("spale@gmail.com")
                .password("12345")
                .role("ADMIN")
                .sex("MALE")
                .build());
        registerController.registerUser(RegisterUserRequest
                .builder()
                .userName("spasoje")
                .firstName("Spasoje")
                .lastName("Petronijevic")
                .email("spale@gmail.com")
                .password("12345")
                .role("NORMAL")
                .sex("MALE")
                .build());
    }
}
