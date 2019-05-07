package com.eventify;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class EventSeeder {
    public static String baseUrl = "http://event:8200";

    public static void seed() throws IOException {//todo handle this exception properly
        List<Event> events = DataSeederApp.getObjectMapper().readValue(EventSeeder.class.getResourceAsStream("/events.json"), new TypeReference<List<Event>>(){});
        log.info("Events that should be seeded: " + events);
        events.forEach(event -> DataSeederApp.getRestTemplate().exchange(
                baseUrl + "/events",
                HttpMethod.POST,
                new HttpEntity<>(event,DataSeederApp.headers)
                ,Object.class)
        );//todo maybe introduce eventresource instead of Object?
    }
}
