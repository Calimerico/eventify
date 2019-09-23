package com.eventify.shared.kafka;

public enum Topic {
    EVENTS_TOPIC("eventsTopic"),
    PLACES_TOPIC("placesTopic");

    private final String name;

    Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
