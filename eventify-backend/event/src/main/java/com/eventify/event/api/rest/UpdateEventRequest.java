package com.eventify.event.api.rest;

import com.eventify.shared.demo.EventType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Data
class UpdateEventRequest {
    @NotBlank
    private String eventName;
    @NotNull
    private EventType eventType;
    private UUID placeId;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    private List<Integer> prices;
}
