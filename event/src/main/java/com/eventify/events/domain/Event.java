package com.eventify.events.domain;

/**
 * Created by spasoje on 01-Nov-18.
 */

import lombok.Builder;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 15-Jun-17.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="event_type")
@Table(name="event")
@EqualsAndHashCode
public class Event {
    //TODO All ids should be UUID, id is dependent on name, date and place, be careful if for example date change you don't have consistency!
    private String eventId;
    private String eventName;
    private Set<UUID> hosts;
    private String eventType;
    //TODO All ids should be UUID!
    private String placeId;
    private LocalDateTime eventDateAndTime;
    private String description;
    private String source;
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

    @Basic
    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    @ElementCollection
    public Set<UUID> getHosts() {
        return hosts;
    }

    public void setHosts(Set<UUID> hosts) {
        this.hosts = hosts;
    }
}

