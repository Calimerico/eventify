package com.eventify.webscraper.api.integration.events.output;

import com.eventify.shared.demo.EventType;
import com.eventify.shared.demo.IntegrationEvent;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Value
@Builder
@ToString
//TODO Is this appropriate package for output event?Introduce domain and external events?
public class EventScraped implements IntegrationEvent {
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

    public static EventScraped fromDomainEvent(com.eventify.webscraper.domain.events.EventScraped eventScraped) {
        return builder()
                .eventName(eventScraped.getEventName())
                .eventHostIds(eventScraped.getEventHostIds())
                .eventType(eventScraped.getEventType())
                .placeId(eventScraped.getPlaceId())
                .eventDateTime(eventScraped.getEventDateTime())
                .description(eventScraped.getDescription())
                .source(eventScraped.getSource())
                .picture(eventScraped.getPicture())
                .prices(eventScraped.getPrices())
                .build();
    }
}
