package com.example.demo;

import lombok.*;

import java.util.List;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Builder
@Data //TODO Should be immutable Value here and final on field but jackson is complaining
@NoArgsConstructor //TODO This no args must be here cos of jackson
@AllArgsConstructor //TODO Try to delete just this one...compile error on builder
public class EventsScraped implements DomainEvent {
    private List<EventScraped> eventsScraped;
}
