package com.eventify.config.security;

import com.eventify.shared.security.Context;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHostFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PermissionService {

    private final UnconfirmedEventsOnHostFinder unconfirmedEventsOnHostFinder;
    private final Context context;

    public boolean hasPermissionToConfirmHostOnEvent(UUID eventId) {
        return unconfirmedEventsOnHostFinder.findUnconfirmedEventsForHost(context.getUserId()).contains(eventId);
    }
}
