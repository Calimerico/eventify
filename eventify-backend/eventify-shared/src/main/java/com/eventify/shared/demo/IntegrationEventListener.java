package com.eventify.shared.demo;

public interface IntegrationEventListener<T extends IntegrationEvent> {
    void handle(T integrationEvent);
}
