package com.eventify.visitedevent.api;

import com.eventify.shared.demo.EventType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Data
class VisitedEventRequest {
    private Integer age;
}
