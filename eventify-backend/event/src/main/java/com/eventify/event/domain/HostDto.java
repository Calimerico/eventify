package com.eventify.event.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class HostDto {
    private UUID id;
    private String name;
}
