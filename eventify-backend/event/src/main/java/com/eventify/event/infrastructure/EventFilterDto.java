package com.eventify.event.infrastructure;

import com.eventify.shared.demo.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

//todo this class is maybe redudant, think about it!
@Data
@Builder
public class EventFilterDto {
    private String eventName;
    private EventType eventType;
    private UUID hostId;
    private UUID placeId;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    private Integer priceFrom;
    private Integer priceTo;
}
