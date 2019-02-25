package com.eventify.events.infrastructure;

import com.eventify.events.domain.Event;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by spasoje on 01-Nov-18.
 */

public interface EventRepository extends CrudRepository<Event,String> {
    Event findByEventName(String eventName);
    Page<Event> findAll(Example example, Pageable pageable);
    Event findByEventId(UUID id);
    boolean removeByEventId(UUID id);
}
