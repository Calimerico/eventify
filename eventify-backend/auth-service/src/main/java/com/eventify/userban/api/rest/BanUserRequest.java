package com.eventify.userban.api.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by spasoje on 13-Dec-18.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BanUserRequest {
    private UUID userId;
    private UUID adminId;
    private String reasonForBan;
    private LocalDateTime bannedUntil;
}
