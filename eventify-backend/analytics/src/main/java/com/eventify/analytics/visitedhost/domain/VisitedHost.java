package com.eventify.analytics.visitedhost.domain;

import com.eventify.shared.demo.Sex;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class VisitedHost {
    private UUID userId;
    private Sex sex;
    private Integer age;
    private UUID hostId;
}
