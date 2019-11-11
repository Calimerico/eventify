package com.eventify.visitedpage.domain;

import com.eventify.shared.ddd.UUIDAggregate;

import java.util.UUID;

public class VisitedPage extends UUIDAggregate {
    private UUID userId;
    private UUID pageId;

    VisitedPage(UUID userId, UUID pageId) {
        this.userId = userId;
        this.pageId = pageId;
    }
}
