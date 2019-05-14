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
public class PlaceSeeder {

    @Value("${eventService.url}")
    private String url;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final IdResolver idResolver;

    public void seed() {//todo handle this exception properly
        try {
            List<Place> places = objectMapper.readValue(PlaceSeeder.class.getResourceAsStream("/data/places.json"), new TypeReference<List<Place>>(){});
            log.info("Places that should be seeded: " + places);
            places.forEach(place -> idResolver.linkId(EntityType.PLACE, restTemplate.exchange(
                    url + "/places",
                    HttpMethod.POST,
                    new HttpEntity<>(place,DataSeeder.httpHeaders)
                    ,Object.class), place.getId())
            );//todo maybe introduce place resource instead of Object?
        } catch (IOException e) {
            e.printStackTrace();//todo
        }

    }
}
