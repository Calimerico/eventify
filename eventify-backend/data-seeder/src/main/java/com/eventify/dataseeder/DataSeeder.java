package com.eventify.dataseeder;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    public static HttpHeaders httpHeaders = new HttpHeaders();
    private final PlaceSeeder placeSeeder;
    private final EventSeeder eventSeeder;
    private final UserSeeder userSeeder;
    private final ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        userSeeder.seed();
        placeSeeder.seed();
        eventSeeder.seed();
        SpringApplication.exit(applicationContext, () -> 0);
    }
}
