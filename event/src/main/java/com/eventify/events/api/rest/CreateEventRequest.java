package com.eventify.events.api.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by spasoje on 30-Nov-18.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data//TODO AllArgs + Data = Value?
@Builder
public class CreateEventRequest {
    private String eventName;
    private String eventType;
    private String placeId;
    private LocalDateTime eventDateAndTime;
    private String description;
    private String source;
}
