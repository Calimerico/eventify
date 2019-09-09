package com.eventify.userban.application.commands;

import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by spasoje on 20-Dec-18.
 */
@Value
@Builder
public class BanUser implements Command<Void> {
    private final UUID userId;
    private final UUID adminId;
    private final String reasonForBan;
    private final LocalDateTime bannedUntil;

}
