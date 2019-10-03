package com.eventify.eventsonhost.api.rest;

import com.eventify.shared.demo.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventHostUnconfirmed implements DomainEvent {
    private UUID eventId;
    private UUID hostId;
}
