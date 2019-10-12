package com.eventify.visitedevent.domain;

import com.eventify.shared.demo.EventType;
import com.eventify.shared.demo.Sex;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class VisitedEvent {
    private UUID userId;
    private Sex sex;
    private Integer age;
    private UUID eventId;
    private EventType eventType;
    private List<UUID> hostIds;
    private  UUID placeId;
}
