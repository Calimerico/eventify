package com.eventify.dataseeder2;

import lombok.Value;

@Value
public class LoginRequest {
    private String username;
    private String password;
}
