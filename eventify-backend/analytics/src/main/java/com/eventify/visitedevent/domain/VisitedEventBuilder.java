package com.eventify.visitedevent.domain;

import com.eventify.shared.demo.EventType;
import com.eventify.shared.demo.Sex;

import java.util.List;
import java.util.UUID;

public class VisitedEventBuilder {
        private UUID userId;
        private Sex sex;
        private Integer age;
        private UUID eventId;
        private EventType eventType;
        private List<UUID> hostIds;
        private UUID placeId;

        public VisitedEventBuilder() {
        }

        public VisitedEventBuilder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public VisitedEventBuilder sex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public VisitedEventBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public VisitedEventBuilder eventId(UUID eventId) {
            this.eventId = eventId;
            return this;
        }

        public VisitedEventBuilder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public VisitedEventBuilder hostIds(List<UUID> hostIds) {
            this.hostIds = hostIds;
            return this;
        }

        public VisitedEventBuilder placeId(UUID placeId) {
            this.placeId = placeId;
            return this;
        }

        public VisitedEvent build() {
            return new VisitedEvent(userId, sex, age, eventId, eventType, hostIds, placeId);
        }
}

