package com.eventify.events;

import com.eventify.shared.demo.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 20-Dec-18.
 */
@Builder
@Data //TODO Should be immutable Value here and final on field but jackson is complaining
@NoArgsConstructor //TODO This no args must be here cos of jackson
@AllArgsConstructor //TODO Try to delete just this one...compile error on builder
public class EventAddedEvent implements DomainEvent {
    private UUID eventId;
    private Set<UUID> hosts;//TODO For now, just user module care about this event, if someone else should care about event adding, add new fields!
}
