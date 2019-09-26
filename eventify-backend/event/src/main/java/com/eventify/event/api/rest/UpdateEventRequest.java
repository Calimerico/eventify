package com.eventify.event.api.rest;

import com.eventify.shared.demo.EventType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Data
class UpdateEventRequest {
    private String eventName;
    private EventType eventType;
    private UUID placeId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    private List<Integer> prices;
}
