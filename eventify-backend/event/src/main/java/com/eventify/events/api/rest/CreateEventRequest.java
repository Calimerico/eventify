package com.eventify.events.api.rest;

import com.eventify.events.domain.EventType;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Data
public class CreateEventRequest {
    private String eventName;
    private EventType eventType;
    private UUID placeId;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    private Set<UUID> hostIds;
    private List<Integer> prices;
}
