package com.eventify.webscraper.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Value
@Builder
//TODO Is this appropriate package for output event?Introduce domain and external events?
public class EventScraped {
    private String eventName;
    private List<UUID> eventHostIds;
//    private Integer eventSeriesId;
    private String eventType;
    private String placeId;
    private LocalDateTime eventDateAndTime;
    private String description;
//    private List<Price> prices;
    private String source;
    private String picture;
}
