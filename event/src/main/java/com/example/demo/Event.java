package com.example.demo;

/**
 * Created by spasoje on 01-Nov-18.
 */

import lombok.Builder;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by spasoje on 15-Jun-17.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="event_type")
@Table(name="event")
@EqualsAndHashCode
public abstract class Event {
    private String eventId;
    private String eventName;
//    private List<String> eventHostsId;
//    private Integer eventSeriesId;
    private String eventType;
    private String placeId;
    private LocalDateTime eventDateAndTime;
    private String description;
    private String source;
//    private Set<User> organizers;//TODO Temporary commented for compile reasons
    //TODO Tickets should be added here

    @Id
    @Column
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

//    @Basic
//    @Column
//    public String getEventHostId() {
//        return eventHostsId;
//    }
//
//    public void setEventHostId(String eventHostId) {
//        this.eventHostsId = eventHostId;
//    }

    @Basic
    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @Basic
//    @Column
//    public Integer getEventSeriesId() {
//        return eventSeriesId;
//    }
//
//    public void setEventSeriesId(Integer eventSeriesId) {
//        this.eventSeriesId = eventSeriesId;
//    }

    @Basic
    @Column(insertable = false, updatable = false)
    public String getEventType() {
        return eventType;
    }

    //TODO Forbid for calling
    protected void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Basic
    @Column
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Basic
    @Column
    public LocalDateTime getEventDateAndTime() {
        return eventDateAndTime;
    }

    public void setEventDateAndTime(LocalDateTime eventDateAndTime) {
        this.eventDateAndTime = eventDateAndTime;
    }

    @Basic
    @Column
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
}

