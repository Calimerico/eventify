package com.eventify.analytics.visitedevent.repository;

import com.eventify.analytics.visitedevent.domain.VisitedEvent;
import com.eventify.analytics.visitedpage.domain.VisitedPage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface VisitedEventRepository extends MongoRepository<VisitedEvent, UUID> {
    public List<VisitedPage> findByUserId(UUID userId);
    public List<VisitedPage> findByEventId(UUID userId);
}
