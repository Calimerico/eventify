package com.eventify.unconfirmedeventsonhost.domain;

import com.eventify.auth.domain.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface UnconfirmedEventsOnHostRepository extends CrudRepository<UnconfirmedEventsOnHost, UUID> {
    Set<UnconfirmedEventsOnHost> findByUnconfirmedEventsContains(UUID eventId);
    UnconfirmedEventsOnHost findByUserId(UUID hostId);
}
