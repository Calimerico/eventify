package com.eventify.shared.demo;

import com.eventify.shared.ddd.DomainEvent;

public interface DomainEventListener<T extends DomainEvent> {
    void handle(T domainEvent);
}
