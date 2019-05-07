package com.eventify;

import lombok.Data;
import lombok.Value;

@Value
public class LoginRequest {
    private String username;
    private String password;
}
