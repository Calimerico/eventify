package com.eventify.events.application.commands;

import com.eventify.events.domain.Event;
import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by spasoje on 15-Dec-18.
 */
@Value
@Builder
public class UpdateEvent implements Command<Event> {
    private UUID id;
    private String eventName;
    private String eventType;
    private String placeId;
    private LocalDateTime eventDateAndTime;
    private String description;
    private String source;
}
