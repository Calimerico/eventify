package com.eventify.events.api.msg;

import com.eventify.events.domain.EventType;
import com.eventify.shared.demo.DomainEvent;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by spasoje on 01-Dec-18.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventScraped implements DomainEvent {
    private String eventName;
    private List<UUID> eventHostIds;
//    private Integer eventSeriesId;
    private EventType eventType;
    private String placeId;
    private LocalDateTime eventDateTime;//TODO Stack overflow problem
    private String description;
        private List<Integer> prices;
    private String source;
    private String picture;

}
