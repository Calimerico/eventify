package com.eventify.dataseeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserSeeder {

    @Value("${authService.url}")
    private String url;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public void seed() {
        try {
            List<User> users = objectMapper.readValue(UserSeeder.class.getResourceAsStream("/data/users.json"), new TypeReference<List<User>>(){});
            log.info("Users that should be seeded: " + users);

            users.forEach(user -> restTemplate.exchange(
                    url + "/users",
                    HttpMethod.POST,
                    new HttpEntity<>(user,DataSeeder.httpHeaders)
                    ,Object.class)
            );
            ResponseEntity<Object> response = restTemplate.exchange(
                    url + "/login",
                    HttpMethod.POST,
                    new HttpEntity<>(new LoginRequest("admin", "12345"), DataSeeder.httpHeaders)
                    , Object.class);

            DataSeeder.httpHeaders.set("Authorization", response.getHeaders().get("Authorization").get(0));
        } catch (IOException e) {
            e.printStackTrace();//todo
        }

    }
}
