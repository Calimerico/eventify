package com.eventify.eventsonhost.api.rest;

import com.eventify.eventsonhost.application.commands.DefaultEventHostConfirmed;
import com.eventify.eventsonhost.application.commands.UnconfirmEventHost;
import com.eventify.user.api.rest.UserController;
import com.eventify.shared.security.Context;
import com.eventify.shared.demo.Gate;
import com.eventify.eventsonhost.application.commands.ConfirmEventHost;
import com.eventify.eventsonhost.domain.UnconfirmedEventsOnHostFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

import static com.eventify.shared.security.RoleName.ROLE_REGISTERED_USER;
import static com.eventify.eventsonhost.api.rest.EventsOnHostController.BASE_PATH;


@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_PATH)
public class EventsOnHostController {
    public static final String BASE_PATH = UserController.BASE_PATH + "/eventsOnHost";
    public static final String CONFIRM_HOST = "/confirm";
    public static final String UNCONFIRM_HOST = "/unconfirm";
    public static final String UNCONFIRMED_EVENTS = "/unconfirmed";
    public static final String ENABLE_HOST_CONFIRMED_BY_DEFAULT = "/hostConfirmedByDefault";
    public static final String DISABLE_HOST_CONFIRMED_BY_DEFAULT = "/hostNotConfirmedByDefault";

    public static final String ID_PATH = "/{eventId}";
    private final Gate gate;
    private final Context context;
    private final UnconfirmedEventsOnHostFinder unconfirmedEventsOnHostFinder;

    @PatchMapping(value = ID_PATH + CONFIRM_HOST)
    @PreAuthorize("@permissionService.hasPermissionToConfirmHostOnEvent(#eventId)")
    public ResponseEntity<Void> confirmHostOnEvent(@PathVariable UUID eventId) {
        gate.dispatch(ConfirmEventHost
                .builder()
                .eventId(eventId)
                .hostId(context.getUserId())
                .build()
        );
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = ID_PATH + UNCONFIRM_HOST)
    @PreAuthorize("@permissionService.hasPermissionToConfirmHostOnEvent(#eventId)")
    public ResponseEntity<Void> unconfirmHostOnEvent(@PathVariable UUID eventId) {
        gate.dispatch(UnconfirmEventHost
                .builder()
                .eventId(eventId)
                .hostId(context.getUserId())
                .build()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = UNCONFIRMED_EVENTS)
    @Secured(ROLE_REGISTERED_USER)
    public ResponseEntity<Set<UUID>> getEventsThatShouldBeConfirmed() {
        return ResponseEntity.ok(unconfirmedEventsOnHostFinder.findUnconfirmedEventsForHost(context.getUserId()));
    }

    @PatchMapping(value = ENABLE_HOST_CONFIRMED_BY_DEFAULT)
    public ResponseEntity<Void> enableDefaultEventHostConfirmed() {
        gate.dispatch(DefaultEventHostConfirmed
                .builder()
                .confirmedByDefault(true)
                .userId(context.getUserId())
                .build()
        );
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = DISABLE_HOST_CONFIRMED_BY_DEFAULT)
    public ResponseEntity<Void> disableDefaultEventHostConfirmed() {
        gate.dispatch(DefaultEventHostConfirmed
                .builder()
                .confirmedByDefault(false)
                .userId(context.getUserId())
                .build()
        );
        return ResponseEntity.ok().build();
    }

}
