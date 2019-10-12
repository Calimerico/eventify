package com.eventify.visitedpage.repository;

import com.eventify.visitedpage.domain.VisitedPage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface VisitedPageRepository extends MongoRepository<VisitedPage, UUID> {
    public List<VisitedPage> findByUserId(UUID userId);
    public List<VisitedPage> findByPageId(UUID userId);
}
