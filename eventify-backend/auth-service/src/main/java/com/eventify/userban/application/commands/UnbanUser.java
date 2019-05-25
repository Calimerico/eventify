package com.eventify.userban.application.commands;

import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UnbanUser implements Command<Void> {
    private UUID userId;
    private UUID adminId;
}
