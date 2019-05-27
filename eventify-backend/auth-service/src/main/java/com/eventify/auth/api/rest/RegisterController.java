package com.eventify.auth.api.rest;

import com.eventify.auth.application.commands.RegisterUser;
import com.eventify.shared.demo.Gate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by spasoje on 13-Dec-18.
 */
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final BCryptPasswordEncoder encoder;
    private final Gate gate;

    @PostMapping("/register")//todo this should be user controller, not register controller...register in name of the url?
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
