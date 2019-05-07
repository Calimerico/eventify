package com.eventify;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
public class DataSeederApp {
    public static HttpHeaders headers = new HttpHeaders();

    public static void main(String[] args) throws IOException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        String profileName = "dockerProfile";
        if (args != null && args.length > 1) {
            profileName = args[0];
        }
        if (profileName.equals("localProfile")) {
            EventSeeder.baseUrl = "http://localhost:8762";
            UserSeeder.baseUrl = "http://localhost:8762";
        }
        log.info("Seeding started");
        UserSeeder.seed();
        EventSeeder.seed();
    }

    public static RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();//todo no dependency injection?
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
