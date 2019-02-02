package com.eventify.events.domain;

/**
 * Created by spasoje on 01-Nov-18.
 */

import lombok.Builder;
import lombok.Data;
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
@Data
@EqualsAndHashCode
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID eventId;
    private String eventName;

    @ElementCollection
    private Set<UUID> hosts;
    private String eventType;
    //TODO All ids should be UUID!
    private String placeId;
    private LocalDateTime eventDateAndTime;
    private String description;
    private String source;
    //TODO Tickets should be added here
}

