package com.eventify.event.api.integration.events.input;

import com.eventify.shared.demo.EventType;
import com.eventify.shared.ddd.DomainEvent;
import com.eventify.shared.demo.IntegrationEvent;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 01-Dec-18.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventScraped implements IntegrationEvent {
    private String eventName;
    private Set<UUID> eventHostIds;
//    private Integer eventSeriesId;
    private EventType eventType;
    private String placeId;
    private LocalDateTime eventDateTime;//TODO Stack overflow problem
    private String description;
    private String source;
    private String picture;
    private Set<Integer> prices;

}
