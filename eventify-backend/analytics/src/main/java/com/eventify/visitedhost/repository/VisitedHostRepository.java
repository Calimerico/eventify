package com.eventify.visitedhost.repository;

import com.eventify.visitedhost.domain.VisitedHost;
import com.eventify.visitedpage.domain.VisitedPage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface VisitedHostRepository extends MongoRepository<VisitedHost, UUID> {
    public List<VisitedPage> findByUserId(UUID userId);
    public List<VisitedPage> findByHostId(UUID userId);
}
