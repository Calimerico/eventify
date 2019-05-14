package com.eventify.dataseeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventSeeder {

    @Value("${eventService.url}")
    private String url;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public void seed() {//todo handle this exception properly
        try {
            List<Event> events = objectMapper.readValue(EventSeeder.class.getResourceAsStream("/data/events.json"), new TypeReference<List<Event>>(){});
            log.info("Events that should be seeded: " + events);
            events.forEach(event -> restTemplate.exchange(
                    url + "/events",
                    HttpMethod.POST,
                    new HttpEntity<>(event,DataSeeder.httpHeaders)
                    ,Object.class)
            );//todo maybe introduce eventresource instead of Object?
        } catch (IOException e) {
            e.printStackTrace();//todo
        }

    }
}

