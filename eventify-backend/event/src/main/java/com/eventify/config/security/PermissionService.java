package com.eventify.config.security;

import com.eventify.event.domain.Event;
import com.eventify.event.infrastructure.EventRepository;
import com.eventify.shared.security.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.eventify.shared.security.RoleName.ROLE_ADMIN;

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
        Event event = eventRepository.loadById(eventId);
        UUID contextUserId = context.getUserId();
        return event.isUserHostForThisEvent(contextUserId);
    }
}
