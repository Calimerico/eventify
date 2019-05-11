package com.eventify.events.domain;

import com.eventify.events.api.msg.EventScraped;
import com.eventify.place.domain.Place;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
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
//        event.setPlaceId(eventScraped.getPlaceId());//TODO
        event.setEventName(eventScraped.getEventName());//TODO Event don't have id at all!!!
        event.setEventType("theater");
        event.setDescription(eventScraped.getDescription());
        event.setEventDateTime(eventScraped.getEventDateTime());
        event.setHosts(null);//TODO
        event.setProfilePicture(eventScraped.getPicture());
        event.setPrices(eventScraped.getPrices());
        return event;
    }

    //TODO This builder smells like hell, something here is wrong
    @Builder(builderMethodName = "aEvent")
    private static Event create(String eventType, String eventName, String description, String source, LocalDateTime eventDateTime, Set<UUID> hosts, Place place, String profilePicture, List<Integer> prices) {
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
        event.setPlace(place);
        event.setEventName(eventName);
        event.setEventType("theater");
        event.setDescription(description);
        event.setPrices(prices);
        event.setEventDateTime(eventDateTime);//TODO Event does not have ID !!!
//        event.setHosts(hosts);//TODO hostRepository?
        event.setProfilePicture(profilePicture);
        return event;
    }
}
