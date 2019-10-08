package com.eventify.shared;

import com.eventify.shared.demo.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
