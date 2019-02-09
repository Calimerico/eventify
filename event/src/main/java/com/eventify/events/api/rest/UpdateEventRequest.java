package com.eventify.events.api.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by spasoje on 21-Nov-18.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data//TODO AllArgs + Data = Value?
@Builder
public class UpdateEventRequest {
    private String eventName;
    private String eventType;
    private UUID placeId;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;

}
