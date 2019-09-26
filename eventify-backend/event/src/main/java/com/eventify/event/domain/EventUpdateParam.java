package com.eventify.event.domain;

import com.eventify.place.domain.Place;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Value
@Builder
public class EventUpdateParam {
    private String eventName;
    private Place place;
    private Set<HostOnEvent> hosts;
    private EventType eventType;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    private List<Integer> prices;
}
