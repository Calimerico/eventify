package com.eventify.webscraper.infrasturcture;

import com.eventify.shared.demo.DomainEvent;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Component
@RequiredArgsConstructor
//TODO I will duplicate this one for now, should be in com.eventify.shared folder
public class KafkaEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    //TODO Hardcoded topic name, think about it
    public void send(DomainEvent event) {
        try {
            //TODO Rename topic name
            kafkaTemplate.send("cqrs",objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            //TODO
            e.printStackTrace();
        }
    }
}
