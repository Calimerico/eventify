package com.eventify.visitedpage.api.rest;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
class VisitedPageRequest {
    @NotBlank
    private UUID userId;
    @NotNull
    private Long pageId;
}
