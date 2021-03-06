package com.eventify.place.infrastructure;

import com.eventify.place.domain.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Created by spasoje on 04-Feb-19.
 */
public interface PlaceRepository extends CrudRepository<Place,UUID> {
    List<Place> findByCity(String city);//TODO Create finder?
    default Place loadById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException("Place with id " + id + " does not exist."));
    }
}
