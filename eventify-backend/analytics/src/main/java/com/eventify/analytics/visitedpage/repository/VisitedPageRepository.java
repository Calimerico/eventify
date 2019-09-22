package com.eventify.analytics.visitedpage.repository;

import com.eventify.analytics.visitedpage.domain.VisitedPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface VisitedPageRepository extends MongoRepository<VisitedPage, UUID> {
    public List<VisitedPage> findByUserId(UUID userId);
    public List<VisitedPage> findByPageId(UUID userId);
}
