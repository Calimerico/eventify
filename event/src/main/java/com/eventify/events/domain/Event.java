package com.eventify.events.domain;

/**
 * Created by spasoje on 01-Nov-18.
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToMany
    private Set<Host> hosts;
    private String eventType;

    @OneToOne
    private Place place;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    //TODO Tickets should be added here
}

