package com.eventify.userban.api.rest;

import com.eventify.shared.demo.Gate;
import com.eventify.userban.application.commands.BanUser;
import com.eventify.userban.application.commands.UnbanUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by spasoje on 13-Dec-18.
 */
@RestController
@RequestMapping("/userbans")
@RequiredArgsConstructor
public class UserBanController {

    private final Gate gate;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> banUser(@RequestBody BanUserRequest banUserRequest) {
        gate.dispatch(BanUser
                .builder()
                .adminId(banUserRequest.getAdminId())
                .bannedUntil(banUserRequest.getBannedUntil())
                .reasonForBan(banUserRequest.getReasonForBan())
                .userId(banUserRequest.getUserId())
                .build());
        return ResponseEntity.ok().build();//TODO Should be status created, not ok
    }

    @PatchMapping("/userbans/")
    public ResponseEntity<Void> unbanUser(@RequestBody UnbanUserRequest unbanUserRequest) {
        gate.dispatch(UnbanUser
                .builder()
                .adminId(unbanUserRequest.getAdminId())
                .userId(unbanUserRequest.getUserId())
                .build());
        return ResponseEntity.ok().build();//TODO Should be status created, not ok
    }

}
