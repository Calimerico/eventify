package com.eventify.dataseeder.listeners;

import com.eventify.dataseeder.event.EventSeeder;
import com.eventify.dataseeder.place.PlaceSeeder;
import com.eventify.dataseeder.user.UserSeeder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${userService.url}")
    private String url;
    public static HttpHeaders httpHeaders = new HttpHeaders();
    private final PlaceSeeder placeSeeder;
    private final EventSeeder eventSeeder;
    private final UserSeeder userSeeder;
    private final ApplicationContext applicationContext;
    private final RestTemplate restTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        userSeeder.seed();
        ResponseEntity<Object> response = restTemplate.exchange(
                url + "/login",
                HttpMethod.POST,
                new HttpEntity<>(new LoginRequest("admin", "12345"), DataSeeder.httpHeaders)
                , Object.class);

        DataSeeder.httpHeaders.set("Authorization", response.getHeaders().get("Authorization").get(0));
        placeSeeder.seed();
        try {
            Thread.sleep(3000);//todo this is waiting for replication of places, think of better solution for this!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eventSeeder.seed();
        SpringApplication.exit(applicationContext, () -> 0);
    }

    @lombok.Value
    private class LoginRequest {
        private String username;
        private String password;
    }
}
