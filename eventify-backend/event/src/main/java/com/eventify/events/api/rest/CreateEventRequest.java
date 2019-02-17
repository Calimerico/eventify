package com.eventify.events.api.rest;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Data
public class CreateEventRequest {
    private String eventName;
    private String eventType;
    private UUID placeId;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    private List<Integer> prices;
}
