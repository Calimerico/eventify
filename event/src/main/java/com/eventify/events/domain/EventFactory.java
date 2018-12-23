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
        switch (eventScraped.getEventType()) {
            case "theater":
                TheaterEvent theaterEvent = new TheaterEvent();
                theaterEvent.setSource(eventScraped.getSource());
                theaterEvent.setPlaceId(eventScraped.getPlaceId());
                theaterEvent.setEventId(eventScraped.getEventId());
                theaterEvent.setEventName(eventScraped.getEventName());
                theaterEvent.setEventType("theater");
                theaterEvent.setDescription(eventScraped.getDescription());
                theaterEvent.setGenre("default genre");//TODO
                theaterEvent.setEventDateAndTime(eventScraped.getEventDateAndTime());
                theaterEvent.setHosts(null);//TODO
                return theaterEvent;
            default:
                throw new RuntimeException("");
        }
    }

    //TODO This builder smells like hell, something here is wrong
    @Builder(builderMethodName = "aEvent")
    public static Event create(String eventType, String eventName, String description, String source, LocalDateTime eventDateAndTime, Set<UUID> hosts, String placeId) {
        switch (eventType) {
            case "theater":
                TheaterEvent theaterEvent = new TheaterEvent();
                theaterEvent.setSource(source);
                theaterEvent.setPlaceId(placeId);
                theaterEvent.setEventId(UUID.randomUUID().toString());//TODO DANGER RANDOM ID!!!
                theaterEvent.setEventName(eventName);
                theaterEvent.setEventType("theater");
                theaterEvent.setDescription(description);
                theaterEvent.setGenre("default genre");//TODO
                theaterEvent.setEventDateAndTime(eventDateAndTime);
                theaterEvent.setHosts(hosts);
                return theaterEvent;
            default:
                throw new RuntimeException("");

        }
    }
}
