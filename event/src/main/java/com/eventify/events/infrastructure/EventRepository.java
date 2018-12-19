package com.eventify.events.infrastructure;

import com.eventify.events.domain.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by spasoje on 01-Nov-18.
 */

public interface EventRepository extends CrudRepository<Event,String> {
    Event findByEventName(String eventName);
}
