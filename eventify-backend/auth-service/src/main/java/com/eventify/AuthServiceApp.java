package com.eventify;

import com.eventify.config.kafka.KafkaStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by spasoje on 25-Nov-18.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableBinding(KafkaStreams.class)
public class AuthServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApp.class, args);
    }
}
