package com.eventify.dataseeder;

import lombok.Value;

@Value
class LoginRequest {
    private String username;
    private String password;
}
