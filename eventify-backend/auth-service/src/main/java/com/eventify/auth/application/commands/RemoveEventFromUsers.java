package com.eventify.auth.application.commands;

import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class RemoveEventFromUsers implements Command<Void> {
    private final UUID eventId;
    private final Set<UUID> hosts;
}
