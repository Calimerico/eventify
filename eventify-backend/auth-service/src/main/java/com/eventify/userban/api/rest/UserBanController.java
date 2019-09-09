package com.eventify.userban.api.rest;

import com.eventify.shared.demo.Gate;
import com.eventify.userban.application.commands.BanUser;
import com.eventify.userban.application.commands.UnbanUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static com.eventify.shared.security.RoleName.ROLE_ADMIN;
import static com.eventify.userban.api.rest.UserBanController.BASE_PATH;

/**
 * Created by spasoje on 13-Dec-18.
 */
@RestController
@RequestMapping(BASE_PATH)
@RequiredArgsConstructor
public class UserBanController {

    public final static String BASE_PATH = "/userbans";
    private final Gate gate;

    @PatchMapping(value = "/unban", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<Void> banUser(@RequestBody BanUserRequest banUserRequest) {
        gate.dispatch(BanUser
                .builder()
                .adminId(banUserRequest.getAdminId())
                .bannedUntil(banUserRequest.getBannedUntil())
                .reasonForBan(banUserRequest.getReasonForBan())
                .userId(banUserRequest.getUserId())
                .build());
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/ban/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured(ROLE_ADMIN)
    public ResponseEntity<Void> unbanUser(@RequestBody UnbanUserRequest unbanUserRequest) {
        gate.dispatch(UnbanUser
                .builder()
                .adminId(unbanUserRequest.getAdminId())
                .userId(unbanUserRequest.getUserId())
                .build());
        return ResponseEntity.ok().build();
    }

}
