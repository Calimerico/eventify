package com;

import com.example.demo.DomainEvent;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Component
public class KafkaEventProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //TODO Hardcoded topic name, think about it
    public void send(DomainEvent event) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            kafkaTemplate.send("cqrs",mapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            //TODO
            e.printStackTrace();
        }
    }
}
