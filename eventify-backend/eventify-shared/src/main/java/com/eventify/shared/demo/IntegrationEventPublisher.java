package com.eventify.shared.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IntegrationEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(IntegrationEvent integrationEvent) {
        applicationEventPublisher.publishEvent(integrationEvent);
    }
}
