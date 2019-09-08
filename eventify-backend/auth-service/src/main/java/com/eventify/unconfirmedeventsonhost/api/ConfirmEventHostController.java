package com.eventify.unconfirmedeventsonhost.api;

import com.eventify.auth.api.rest.UserController;
import com.eventify.shared.demo.Context;
import com.eventify.shared.demo.Gate;
import com.eventify.unconfirmedeventsonhost.application.commands.ConfirmEventHost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.eventify.unconfirmedeventsonhost.api.ConfirmEventHostController.BASE_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.BASE_PATH + BASE_PATH)
public class ConfirmEventHostController {
    public static final String BASE_PATH = "/eventHostConfirms";
    public static final String ID_PATH = "/{eventId}";
    private final Gate gate;
    private final Context context;

    @PatchMapping(value = ID_PATH)
    public ResponseEntity<Void> confirmHostOnEvent(@PathVariable UUID eventId) {
        gate.dispatch(ConfirmEventHost
                .builder()
                .eventId(eventId)
                .hostId(context.getUserId())
                .build()
        );
        return ResponseEntity.ok().build();
    }

}
