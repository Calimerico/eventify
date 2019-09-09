package com.eventify.shared.kafka;

public enum Topic {
    EVENTS_TOPIC("eventsTopic");

    private final String name;

    Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
