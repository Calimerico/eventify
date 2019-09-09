package com.eventify.unconfirmedeventsonhost.api.rest;

import com.eventify.unconfirmedeventsonhost.application.commands.DefaultEventHostConfirmed;
import com.eventify.user.api.rest.UserController;
import com.eventify.shared.security.Context;
import com.eventify.shared.demo.Gate;
import com.eventify.unconfirmedeventsonhost.application.commands.ConfirmEventHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHostFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

import static com.eventify.shared.security.RoleName.ROLE_REGISTERED_USER;
import static com.eventify.unconfirmedeventsonhost.api.rest.ConfirmEventHostController.BASE_PATH;


@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_PATH)
public class ConfirmEventHostController {
    public static final String BASE_PATH = UserController.BASE_PATH + "/eventHostConfirms";
    public static final String ID_PATH = "/{eventId}";
    private final Gate gate;
    private final Context context;
    private final UnconfirmedEventsOnHostFinder unconfirmedEventsOnHostFinder;

    @PatchMapping(value = ID_PATH)
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

    @GetMapping
    @Secured(ROLE_REGISTERED_USER)
    public ResponseEntity<Set<UUID>> getEventsThatShouldBeConfirmed() {
        return ResponseEntity.ok(unconfirmedEventsOnHostFinder.findUnconfirmedEventsForHost(context.getUserId()));
    }

    @PatchMapping(value = "/enableConfirmEventHost")
    public ResponseEntity<Void> enableDefaultEventHostConfirmed() {
        gate.dispatch(DefaultEventHostConfirmed
                .builder()
                .confirmedByDefault(true)
                .userId(context.getUserId())
                .build()
        );
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/disableConfirmEventHost")
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
