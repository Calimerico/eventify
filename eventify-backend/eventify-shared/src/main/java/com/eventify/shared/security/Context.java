package com.eventify.shared.security;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Context {
    private ThreadLocal<UUID> userId = new ThreadLocal<>();

    public UUID getUserId() {
        return userId.get();
    }

    void setUserId(UUID userId) {
        this.userId.set(userId);
    }
}
