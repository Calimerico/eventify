package com.eventify.events.api.rest;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by spasoje on 02-Feb-19.
 */
@Value
@Builder
//TODO Maybe this filter is in wrong package and has bad name?
public class EventFilter {
    private final String eventName;
    private final String eventType;
    private final UUID hostId;
    private final UUID placeId;
    private final LocalDateTime timeFrom;
    private final LocalDateTime timeTo;
}
