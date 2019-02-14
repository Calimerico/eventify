package com.eventify.events.infrastructure;

import com.eventify.events.domain.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by spasoje on 04-Feb-19.
 */
public interface PlaceRepository extends CrudRepository<Place,UUID> {
}
