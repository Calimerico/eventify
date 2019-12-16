package com.eventify.visitedpage.api.rest;

import com.eventify.visitedpage.domain.VisitedPage;
import lombok.Builder;
import lombok.Value;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Builder
@Value
@Relation(value = "resource", collectionRelation = "resources")
class VisitedPageResource extends ResourceSupport {

    private String pageId;
    private String userId;

    public static VisitedPageResource fromEvent(VisitedPage visitedPage) {
        return VisitedPageResource.builder()
                .pageId(visitedPage.getPageId().toString())
                .userId(visitedPage.getUserId().toString())
                .build();
    }
}
