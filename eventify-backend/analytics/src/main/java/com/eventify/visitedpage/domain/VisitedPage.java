package com.eventify.visitedpage.domain;

import org.springframework.data.annotation.Id;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class VisitedPage {
    private UUID userId;
    private UUID pageId;
}
