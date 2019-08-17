package com.eventify.events.api.rest;

import com.eventify.events.domain.EventType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by spasoje on 10-Feb-19.
 */
@Data
public class EventFilterBean {
    private String eventName;
    private EventType eventType;
    private UUID hostId;
    private UUID placeId;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    private Integer priceFrom;
    private Integer priceTo;
}
