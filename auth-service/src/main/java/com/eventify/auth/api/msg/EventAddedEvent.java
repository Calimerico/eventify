package com.eventify.auth.api.msg;

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
    private String eventId;
    private Set<UUID> hosts;
}
