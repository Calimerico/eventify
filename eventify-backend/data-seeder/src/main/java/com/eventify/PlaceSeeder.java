package com.eventify;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.List;

@Slf4j
public class PlaceSeeder {
    public static String baseUrl = "http://event:8200";

    public static void seed() throws IOException {//todo handle this exception properly
        List<Place> places = DataSeederApp.getObjectMapper().readValue(PlaceSeeder.class.getResourceAsStream("/places.json"), new TypeReference<List<Place>>(){});
        log.info("Places that should be seeded: " + places);
        places.forEach(event -> DataSeederApp.getRestTemplate().exchange(
                baseUrl + "/places",
                HttpMethod.POST,
                new HttpEntity<>(event,DataSeederApp.headers)
                ,Object.class)
        );//todo maybe introduce place resource instead of Object?
    }
}
