package com.example.demo;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by spasoje on 01-Dec-18.
 */
@Builder
@Data //TODO Should be immutable Value here and final on field but jackson is complaining
@NoArgsConstructor //TODO This no args must be here cos of jackson
@AllArgsConstructor //TODO Try to delete just this one...compile error on builder
public class EventScraped implements DomainEvent {
    private String eventId;
    private String eventName;
    private List<String> eventHostIds;
//    private Integer eventSeriesId;
    private String eventType;
    private String placeId;
    private LocalDateTime eventDateAndTime;//TODO Stack overflow problem
    private String description;
    //    private List<Price> prices;
    private String source;
    private String picture;

}
