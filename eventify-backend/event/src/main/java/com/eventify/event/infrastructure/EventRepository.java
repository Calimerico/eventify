package com.eventify.event.infrastructure;

import com.eventify.event.domain.Event;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Created by spasoje on 01-Nov-18.
 */

public interface EventRepository extends CrudRepository<Event,UUID> {
    Event findByEventName(String eventName);
    default Event loadById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException("Event with id " + id + " does not exist!"));
    }
    Page<Event> findAll(Example example, Pageable pageable);
}
