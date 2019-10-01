package com.eventify.user.application.commands;

import com.eventify.shared.demo.Command;
import com.eventify.shared.demo.Sex;
import lombok.Builder;
import lombok.Value;

/**
 * Created by spasoje on 13-Dec-18.
 */
@Value
@Builder
public class RegisterUser implements Command<Void> {
    private final String email;
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final Sex sex;
    private final String role;
}
