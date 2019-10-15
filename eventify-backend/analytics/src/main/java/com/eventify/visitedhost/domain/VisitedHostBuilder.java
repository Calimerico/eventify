package com.eventify.visitedhost.domain;

import com.eventify.shared.demo.Sex;

import java.util.UUID;


public  class VisitedHostBuilder {
        private UUID userId;
        private Sex sex;
        private Integer age;
        private UUID hostId;

        public VisitedHostBuilder() {
        }

        public VisitedHostBuilder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public VisitedHostBuilder sex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public VisitedHostBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public VisitedHostBuilder hostId(UUID hostId) {
            this.hostId = hostId;
            return this;
        }

        public VisitedHost build() {
            return new VisitedHost(userId, sex, age, hostId);
        }
}

