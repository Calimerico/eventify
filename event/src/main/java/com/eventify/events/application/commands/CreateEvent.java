package com.eventify.events.application.commands;

import com.eventify.events.domain.Event;
import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 15-Dec-18.
 */
@Value
@Builder
public class CreateEvent implements Command<Event> {
    private String eventName;
    private String eventType;
    private String placeId;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private Set<UUID> hosts;
    private String profilePicture;
}
