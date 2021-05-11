package com.eventify.event.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class IncidentUpdateParam {
    UUID personId;
    UUID carId;
    LocalDateTime dateTimeReported;
    LocalDateTime incidentDateTime;
}
