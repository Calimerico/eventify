package com.eventify.visitedhost.domain;

import com.eventify.shared.ddd.UUIDAggregate;
import com.eventify.shared.demo.Sex;

import java.util.UUID;

public class VisitedHost extends UUIDAggregate {
    private UUID userId;
    private Sex sex;
    private Integer age;
    private UUID hostId;

    VisitedHost(UUID userId, Sex sex, Integer age, UUID hostId) {
        this.userId = userId;
        this.sex = sex;
        this.age = age;
        this.hostId = hostId;
    }
}
