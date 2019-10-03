package com.eventify.eventsonhost.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UnconfirmedEventsOnHostFinder {
    private final EventsOnHostRepository eventsOnHostRepository;

    public Set<UUID> findUnconfirmedEventsForHost(UUID hostId) {
        return eventsOnHostRepository.findByHostId(hostId).getUnconfirmedEvents();
    }
}
