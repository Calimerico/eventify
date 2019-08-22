package com.eventify.events.api.rest;

import com.eventify.events.domain.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)//todo test this with arch unit test
    private LocalDateTime timeFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeTo;
    private Integer priceFrom;
    private Integer priceTo;
}
