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
        Event event;
        if (eventScraped.getEventType() == null) {
            event = new Event();
        } else {
            switch (eventScraped.getEventType()) {
                case "theater":
                    event = new TheaterEvent();
                    break;
                default:
                    throw new RuntimeException("");//TODO
            }
        }
        event.setSource(eventScraped.getSource());
        event.setPlaceId(eventScraped.getPlaceId());
        event.setEventName(eventScraped.getEventName());//TODO Event don't have id at all!!!
        event.setEventType("theater");
        event.setDescription(eventScraped.getDescription());
        event.setEventDateTime(eventScraped.getEventDateTime());
        event.setHosts(null);//TODO
        event.setProfilePicture(eventScraped.getPicture());
        return event;
    }

    //TODO This builder smells like hell, something here is wrong
    @Builder(builderMethodName = "aEvent")
    public static Event create(String eventType, String eventName, String description, String source, LocalDateTime eventDateTime, Set<UUID> hosts, String placeId, String profilePicture) {
        Event event;
        if (eventType == null) {
            event = new Event();
        } else {
            switch (eventType) {
                case "theater":
                    event = new TheaterEvent();
                    break;
                default:
                    throw new RuntimeException("");//TODO
            }
        }
        event.setSource(source);
        event.setPlaceId(placeId);
        event.setEventName(eventName);
        event.setEventType("theater");
        event.setDescription(description);
        event.setEventDateTime(eventDateTime);
        event.setHosts(hosts);//TODO Event does not have ID !!!
        event.setProfilePicture(profilePicture);
        return event;
    }
}
