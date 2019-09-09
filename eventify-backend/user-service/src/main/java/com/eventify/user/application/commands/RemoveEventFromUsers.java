package com.eventify.user.application.commands;

import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class RemoveEventFromUsers implements Command<Void> {
    private final UUID eventId;
    private final Set<UUID> confirmedHosts;
    private final Set<UUID> unconfirmedHosts;
}
