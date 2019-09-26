package com.eventify.event.api.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostResource {
    private UUID id;
    private String name;
    private boolean confirmed;
}
