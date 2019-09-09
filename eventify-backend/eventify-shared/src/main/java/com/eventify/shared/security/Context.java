package com.eventify.shared.security;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Context {
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    void setUserId(UUID userId) {
        this.userId = userId;
    }
}
