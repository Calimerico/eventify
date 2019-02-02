package com.eventify.events.infrastructure;

import com.eventify.events.domain.Event;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by spasoje on 01-Nov-18.
 */

public interface EventRepository extends CrudRepository<Event,String> {
    Event findByEventName(String eventName);
    List<Event> findAll(Example example);
    Event findByEventId(UUID id);
    boolean remove(UUID id);
}
