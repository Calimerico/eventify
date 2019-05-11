package com.eventify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//TODO Introduce my annotation where I will include those 3 lombok annotations?
@Data
@ToString
@JsonIgnoreProperties(value = "id", allowGetters = true)
public class Event {
    private int id;
    private String eventName;
    private String eventType;
    private UUID placeId;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    private List<Integer> prices;
}
