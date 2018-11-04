package com.example.demo;

/**
 * Created by spasoje on 01-Nov-18.
 */

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Created by spasoje on 15-Jun-17.
 */
@Entity
@Inheritance
@DiscriminatorColumn(name="event_type")
@Table(name="event")
public class Event {
    private String eventId;
    private String eventName;
    private String eventHostId;
    private Integer eventSeriesId;
    private String eventType;
    private String placeId;
    private ZonedDateTime eventDateAndTime;
    private String description;
    private String source;
//    private Set<User> organizers;//TODO Temporary commented for compile reasons
    //TODO Tickets should be added here

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "event_name")
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Basic
    @Column(name = "event_host_id")
    public String getEventHostId() {
        return eventHostId;
    }

    public void setEventHostId(String eventHostId) {
        this.eventHostId = eventHostId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "event_series_id")
    public Integer getEventSeriesId() {
        return eventSeriesId;
    }

    public void setEventSeriesId(Integer eventSeriesId) {
        this.eventSeriesId = eventSeriesId;
    }

    @Basic
    @Column(name = "event_type", insertable = false, updatable = false)
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Basic
    @Column(name = "place_id")
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Basic
    @Column(name = "event_date_and_time")
    public ZonedDateTime getEventDateAndTime() {
        return eventDateAndTime;
    }

    public void setEventDateAndTime(ZonedDateTime eventDateAndTime) {
        this.eventDateAndTime = eventDateAndTime;
    }

    @Basic
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

//    @ManyToMany (mappedBy = "eventsOrganizedByUser")
//    public Set<User> getOrganizers(){
//        return organizers;
//    }
//
//    public void setOrganizers(Set<User> organizers) {
//        this.organizers = organizers;
//    }//TODO Temporary commented for compile reasons

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!eventId.equals(event.eventId)) return false;
        if (!eventName.equals(event.eventName)) return false;
        if (!eventHostId.equals(event.eventHostId)) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (eventSeriesId != null ? !eventSeriesId.equals(event.eventSeriesId) : event.eventSeriesId != null)
            return false;
        if (!eventType.equals(event.eventType)) return false;
        if (!placeId.equals(event.placeId)) return false;
        if (!eventDateAndTime.equals(event.eventDateAndTime)) return false;
        return source.equals(event.source);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + eventName.hashCode();
        result = 31 * result + eventHostId.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (eventSeriesId != null ? eventSeriesId.hashCode() : 0);
        result = 31 * result + eventType.hashCode();
        result = 31 * result + placeId.hashCode();
        result = 31 * result + eventDateAndTime.hashCode();
        result = 31 * result + source.hashCode();
        return result;
    }
}

