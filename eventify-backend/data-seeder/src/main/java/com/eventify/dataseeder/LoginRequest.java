package com.eventify.dataseeder;

import lombok.Value;

@Value
public class LoginRequest {
    private String username;
    private String password;
}
