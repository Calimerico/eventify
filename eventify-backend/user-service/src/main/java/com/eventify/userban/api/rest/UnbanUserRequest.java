package com.eventify.userban.api.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
class UnbanUserRequest {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID adminId;
}
