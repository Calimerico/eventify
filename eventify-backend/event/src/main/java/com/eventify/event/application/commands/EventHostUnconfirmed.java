package com.eventify.event.application.commands;

import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class EventHostUnconfirmed implements Command<Void> {
    private UUID eventId;
    private UUID hostId;
}
