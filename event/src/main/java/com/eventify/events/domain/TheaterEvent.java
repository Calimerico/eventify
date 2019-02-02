package com.eventify.events.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Builder
@Data
@Entity
public class TheaterEvent extends Event {
    private String genre;

    public TheaterEvent(String genre) {
        this.genre = genre;
        this.setEventType("theater");
    }

    public TheaterEvent() {
        this.setEventType("theater");
    }

}
