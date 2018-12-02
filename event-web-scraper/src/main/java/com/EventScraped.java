package com;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Value
@Builder
public class EventScraped {
    private String eventId;
    private String eventName;
    private List<String> eventHostIds;
//    private Integer eventSeriesId;
    private String eventType;
    private String placeId;
    private LocalDateTime eventDateAndTime;//TODO com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion (StackOverflowError) (through reference chain: java.time.zone.ZoneRules["standardOffsets"]->java.time.ZoneOffset[0]->java.time.ZoneOffset["rules"] Caused by: java.lang.StackOverflowError
    private String description;
//    private List<Price> prices;
    private String source;
    private String picture;
}
