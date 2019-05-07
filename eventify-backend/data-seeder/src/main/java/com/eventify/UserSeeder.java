package com.eventify;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class UserSeeder {
    public static String baseUrl = "http://auth:9100";

    public static void seed() throws IOException {
        List<User> users = DataSeederApp.getObjectMapper().readValue(UserSeeder.class.getResourceAsStream("/users.json"), new TypeReference<List<User>>(){});
        log.info("Users that should be seeded: " + users);

        users.forEach(user -> DataSeederApp.getRestTemplate().exchange(
                baseUrl + "/auth/register",
                HttpMethod.POST,
                new HttpEntity<>(user,DataSeederApp.headers)
                ,Object.class)
        );//todo maybe introduce eventresource instead of Object?
        ResponseEntity<Object> response = DataSeederApp.getRestTemplate().exchange(
                baseUrl + "/auth/login",
                HttpMethod.POST,
                new HttpEntity<>(new LoginRequest("admin", "12345"), DataSeederApp.headers)
                , Object.class);

        DataSeederApp.headers.setBearerAuth(response.getHeaders().get("Authorization").get(0));
    }
}
