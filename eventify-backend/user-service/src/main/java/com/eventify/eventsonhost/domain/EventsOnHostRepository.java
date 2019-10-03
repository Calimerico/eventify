package com.eventify.eventsonhost.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface EventsOnHostRepository extends CrudRepository<EventsOnHost, UUID> {
    Set<EventsOnHost> findByUnconfirmedEventsContains(UUID eventId);
    EventsOnHost findByHostId(UUID hostId);
}
