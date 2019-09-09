package com.eventify.user.api.rest;

import com.eventify.user.application.commands.RegisterUser;
import com.eventify.shared.demo.Gate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by spasoje on 13-Dec-18.
 */
@RestController
@RequestMapping(value = UserController.BASE_PATH)
@RequiredArgsConstructor
public class UserController {

    public static final String BASE_PATH = "/users";
    private final BCryptPasswordEncoder encoder;
    private final Gate gate;

    @PostMapping
    public ResponseEntity registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        gate.dispatch(RegisterUser
                .builder()
                .email(registerUserRequest.getEmail())
                .username(registerUserRequest.getUsername())
                .password(encoder.encode(registerUserRequest.getPassword()))
                .firstName(registerUserRequest.getFirstName())
                .lastName(registerUserRequest.getLastName())
                .sex(registerUserRequest.getSex())
                .role(registerUserRequest.getRole())
                .build());
        return ResponseEntity.ok().build();//TODO Should be status created, not ok
    }

}
