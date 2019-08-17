package com.eventify.events.domain;

/**
 * Created by spasoje on 01-Nov-18.
 */

import lombok.*;
import com.eventify.place.domain.Place;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 15-Jun-17.
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode//todo
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID eventId;
    private String eventName;

    @OneToMany
    private Set<Host> hosts;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @OneToOne
    private Place place;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    @ElementCollection
    private List<Integer> prices;//TODO Introduce Ticket entity or maybe embeddable?
}

