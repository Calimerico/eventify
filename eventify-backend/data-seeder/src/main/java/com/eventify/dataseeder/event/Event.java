package com.eventify.dataseeder.event;

import com.eventify.dataseeder.deserializers.IntegerSetToUUIDSetDeserializer;
import com.eventify.dataseeder.deserializers.IntegerToUUIDDeserializer;
import com.eventify.shared.demo.EventType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//TODO Introduce my annotation where I will include those 3 lombok annotations?
@Data
@ToString
@JsonIgnoreProperties(value = "id", allowGetters = true)
public class Event {
    private int id;
    private String eventName;
    private EventType eventType;
    @JsonDeserialize(using = IntegerToUUIDDeserializer.class)
    private UUID placeId;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    private List<Integer> prices;
    @JsonDeserialize(using = IntegerSetToUUIDSetDeserializer.class)
    private Set<UUID> hostIds;
}
