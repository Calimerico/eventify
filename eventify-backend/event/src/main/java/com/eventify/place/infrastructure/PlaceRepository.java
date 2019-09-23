package com.eventify.place.infrastructure;


import com.eventify.place.domain.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

//todo move this repo to infrastructure somehow(maybe I should introduce DTO Class) Can I update Place through Event class? That should not be allowed
public interface PlaceRepository extends CrudRepository<Place, UUID> {
    default Place loadById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException("Element with id " + id + " does not exist."));
    }
}
