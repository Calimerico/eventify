package com.eventify.webscraper.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Value
@Builder
@ToString
//TODO Is this appropriate package for output event?Introduce domain and external events?
public class EventScraped {
    private String eventName;
    private Set<UUID> eventHostIds;
//    private Integer eventSeriesId;
    private EventType eventType;
    private String placeId;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String picture;
    private Set<Integer> prices;
}
