package com.eventify.shared.ddd;

import com.eventify.shared.DomainEventPublisher;
import com.eventify.shared.demo.DomainEvent;

public abstract class UUIDAggregate extends UUIDEntity {

    private DomainEventPublisher domainEventPublisher;

    protected UUIDAggregate(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    protected void publishEvent(DomainEvent event) {
        domainEventPublisher.publish(event);
    }
}
