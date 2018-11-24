package com.example.demo;

/**
 * Created by spasoje on 21-Nov-18.
 */
public class EventFactory {
    public static Event create(String eventType) {
        switch (eventType) {
            case "theater":
                return new TheaterEvent();
            default:
                throw new RuntimeException("");
        }
    }
}
