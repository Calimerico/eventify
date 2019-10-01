package com.eventify.event.api.rest;

import com.eventify.shared.demo.EventType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by spasoje on 10-Feb-19.
 */
@Data
class EventFilterRequest {
    private String eventName;
    private EventType eventType;
    private UUID hostId;
    private UUID placeId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeTo;
    @PositiveOrZero
    private Integer priceFrom;
    private Integer priceTo;
}
