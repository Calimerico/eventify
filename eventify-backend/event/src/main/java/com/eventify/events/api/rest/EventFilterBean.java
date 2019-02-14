package com.eventify.events.api.rest;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by spasoje on 10-Feb-19.
 */
@Data
public class EventFilterBean {
    private String eventName;
    private String eventType;
    private UUID hostId;
    private UUID placeId;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
}
