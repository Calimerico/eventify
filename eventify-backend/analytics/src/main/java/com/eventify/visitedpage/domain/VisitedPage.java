package com.eventify.visitedpage.domain;

import com.eventify.shared.ddd.UUIDAggregate;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.UUID;

@Getter(AccessLevel.PUBLIC)
public class VisitedPage extends UUIDAggregate {
    private UUID userId;
    private Long pageId;

    VisitedPage(UUID userId, Long pageId) {
        this.userId = userId;
        this.pageId = pageId;
    }
}
