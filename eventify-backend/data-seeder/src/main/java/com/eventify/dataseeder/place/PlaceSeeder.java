package com.eventify.dataseeder.place;

import com.eventify.dataseeder.listeners.DataSeeder;
import com.eventify.dataseeder.idresolver.EntityType;
import com.eventify.dataseeder.idresolver.IdResolver;
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

    @Value("${placeService.url}")
    private String url;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public void seed() {//todo handle this exception properly
        try {
            List<Place> places = objectMapper.readValue(PlaceSeeder.class.getResourceAsStream("/data/places.json"), new TypeReference<List<Place>>(){});
            log.info("Places that should be seeded: " + places);
            places.forEach(place -> IdResolver.linkId(EntityType.PLACE, restTemplate.exchange(
                    url + "/places",
                    HttpMethod.POST,
                    new HttpEntity<>(place, DataSeeder.httpHeaders)
                    ,Object.class).getBody(), place.getId())
            );
        } catch (IOException e) {
            e.printStackTrace();//todo
        }

    }
}
