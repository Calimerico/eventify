package com.eventify.event.application.commands;

import com.eventify.event.domain.Event;
import com.eventify.shared.demo.EventType;
import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 15-Dec-18.
 */
@Value
@Builder
public class CreateEvent implements Command<Event> {
    private String eventName;
    private EventType eventType;
    private UUID placeId;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private Set<UUID> hosts;
    private String profilePicture;
    private List<Integer> prices;
    private UUID createdBy;
    private LocalDateTime createdDateTime;
}
