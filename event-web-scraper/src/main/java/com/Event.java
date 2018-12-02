package com;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by spasoje on 18-Nov-18.
 */
public class Event {
    private String eventId;
    private String eventName;
    private List<String> eventHostIds;
//    private Integer eventSeriesId;
    private String eventType;
    private String placeId;
    private ZonedDateTime eventDateAndTime;
    private String description;
    private List<Price> prices;
    private String source;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<String> getEventHostIds() {
        return eventHostIds;
    }

    public void setEventHostIds(List<String> eventHostIds) {
        this.eventHostIds = eventHostIds;
    }

//    public Integer getEventSeriesId() {
//        return eventSeriesId;
//    }
//
//    public void setEventSeriesId(Integer eventSeriesId) {
//        this.eventSeriesId = eventSeriesId;
//    }


    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public ZonedDateTime getEventDateAndTime() {
        return eventDateAndTime;
    }

    public void setEventDateAndTime(ZonedDateTime eventDateAndTime) {
        this.eventDateAndTime = eventDateAndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventHostIds=" + eventHostIds +
//                ", eventSeriesId=" + eventSeriesId +
                ", eventType='" + eventType + '\'' +
                ", placeId='" + placeId + '\'' +
                ", eventDateAndTime=" + eventDateAndTime +
                ", description='" + description + '\'' +
                ", prices=" + prices +
                ", source='" + source + '\'' +
                '}';
    }
}
