package com.eventify.visitedevent.repository;

import com.eventify.visitedevent.domain.VisitedEvent;
import com.eventify.visitedpage.domain.VisitedPage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface VisitedEventRepository extends MongoRepository<VisitedEvent, UUID> {
    public List<VisitedPage> findByUserId(UUID userId);
    public List<VisitedPage> findByEventId(UUID userId);
}
