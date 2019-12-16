package com.eventify.visitedpage.domain;


import java.util.UUID;


public class VisitedPageBuilder {
    private UUID userId;
    private Long pageId;

     public VisitedPageBuilder() {
    }

    public VisitedPageBuilder userId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public VisitedPageBuilder pageId(Long pageId) {
        this.pageId = pageId;
        return this;
    }

    public VisitedPage build() {
        return new VisitedPage(userId, pageId);
    }
}