package com.example.demo;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by spasoje on 01-Nov-18.
 */

public interface EventRepository extends CrudRepository<Event,String> {
}
