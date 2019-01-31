package com.eventify.events.domain;

import com.eventify.events.api.msg.EventScraped;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 21-Nov-18.
 */
public class EventFactory {
    public static Event create(EventScraped eventScraped) {
        if (eventScraped == null) {
            return null;
        }
        Event event = new Event();
        event.setSource(eventScraped.getSource());
        event.setPlaceId(eventScraped.getPlaceId());
        event.setEventId(eventScraped.getEventId());
        event.setEventName(eventScraped.getEventName());
        event.setEventType("theater");
        event.setDescription(eventScraped.getDescription());
        event.setEventDateAndTime(eventScraped.getEventDateAndTime());
        event.setHosts(null);//TODO
        switch (eventScraped.getEventType()) {
            case "theater":
                ((TheaterEvent) event).setGenre("default genre");//TODO
                break;
        }
        return event;
    }

    //TODO This builder smells like hell, something here is wrong
    @Builder(builderMethodName = "aEvent")
    public static Event create(String eventType, String eventName, String description, String source, LocalDateTime eventDateAndTime, Set<UUID> hosts, String placeId) {
        Event event = new Event();
        event.setSource(source);
        event.setPlaceId(placeId);
        event.setEventId(UUID.randomUUID().toString());//TODO DANGER RANDOM ID!!!
        event.setEventName(eventName);
        event.setEventType("theater");
        event.setDescription(description);
        event.setEventDateAndTime(eventDateAndTime);
        event.setHosts(hosts);
        switch (eventType) {
            case "theater":
                ((TheaterEvent) event).setGenre("default genre");//TODO
                break;
        }
        return event;
    }
}
