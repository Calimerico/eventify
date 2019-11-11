package com.eventify.visitedevent.domain;

import com.eventify.shared.ddd.UUIDAggregate;
import com.eventify.shared.demo.EventType;
import com.eventify.shared.demo.Sex;

import java.util.List;
import java.util.UUID;

public class VisitedEvent extends UUIDAggregate {
    private UUID userId;
    private Sex sex;
    private Integer age;
    private UUID eventId;
    private EventType eventType;
    private List<UUID> hostIds;
    private  UUID placeId;

    VisitedEvent(UUID userId, Sex sex, Integer age, UUID eventId, EventType eventType, List<UUID> hostIds, UUID placeId) {
        this.userId = userId;
        this.sex = sex;
        this.age = age;
        this.eventId = eventId;
        this.eventType = eventType;
        this.hostIds = hostIds;
        this.placeId = placeId;
    }
}
