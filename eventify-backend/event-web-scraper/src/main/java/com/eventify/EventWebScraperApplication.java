package com.eventify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Created by spasoje on 18-Nov-18.
 */
@SpringBootApplication
@EnableKafka
@EnableEurekaClient
public class EventWebScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventWebScraperApplication.class, args);
    }
}
