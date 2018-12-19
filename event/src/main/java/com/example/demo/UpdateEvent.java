package com.example.demo;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * Created by spasoje on 15-Dec-18.
 */
@Value
@Builder
public class UpdateEvent implements Command<Void>{
    private String eventName;
    private String eventType;
    private String placeId;
    private LocalDateTime eventDateAndTime;
    private String description;
    private String source;
}
