package com.eventify.auth.application.commands;

import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

/**
 * Created by spasoje on 20-Dec-18.
 */
@Value
@Builder
public class MakeUserHostOfEvent implements Command<Void> {
    private final UUID userId;
    private final String eventId;//TODO Should be changed to UUID

}
