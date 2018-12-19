package com.eventify.events;

/**
 * Created by spasoje on 21-Nov-18.
 */
public class EventBuilder {
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
//                theaterEvent.setEventSeriesId(eventScraped.getEventSeriesId());
                theaterEvent.setEventType("theater");
                theaterEvent.setDescription(eventScraped.getDescription());
                theaterEvent.setGenre("default genre");//TODO
                theaterEvent.setEventDateAndTime(eventScraped.getEventDateAndTime());
//                theaterEvent.setEventHostId(eventScraped.getEventHostIds());
                return theaterEvent;
            default:
                throw new RuntimeException("");
        }
    }
    //TODO Make builder
//    @Builder(builderMethodName = "aEvent")
//    public static Event create(String eventType, String eventName, String description, ) {
//        return null;
//    }
}
