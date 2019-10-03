package com.eventify.eventsonhost.application.commands;

import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AddUnconfirmedEventOnHost implements Command<Void> {
    private UUID eventId;
    private UUID hostId;
}
