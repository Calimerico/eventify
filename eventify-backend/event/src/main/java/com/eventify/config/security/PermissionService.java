package com.eventify.config.security;

import com.eventify.events.domain.Event;
import com.eventify.events.domain.Host;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.shared.demo.Context;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static com.eventify.shared.demo.RoleName.ROLE_ADMIN;
import static java.util.stream.Collectors.*;

@Component
@RequiredArgsConstructor
public class PermissionService {

    private final Context context;
    private final EventRepository eventRepository;

    public boolean hasPermissionToModifyEvent(UUID eventId) {
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .anyMatch(o -> o.getAuthority().equals(ROLE_ADMIN));
        if (isAdmin) {
            return true;
        }
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NoSuchElementException("Event with id " + eventId + " does not exist"));
        Set<UUID> hostIds = CollectionUtils.emptyIfNull(event.getHosts())
                .stream()
                .map(Host::getId)
                .collect(toSet());
        return hostIds.contains(context.getUserId());
    }
}
