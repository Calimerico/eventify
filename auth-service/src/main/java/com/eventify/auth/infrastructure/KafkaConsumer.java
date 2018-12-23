package com.eventify.auth.infrastructure;

import com.eventify.auth.api.msg.EventAddedEvent;
import com.eventify.auth.application.commands.MakeUserHostOfEvent;
import com.eventify.shared.demo.Gate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by spasoje on 20-Dec-18.
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final Gate gate;

    @KafkaListener(topics = "cqrs1")
    public void receiveTopic(ConsumerRecord<?, String> consumerRecord) {
        String domainEventAsString = consumerRecord.value();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule()); // new module, NOT JSR310Module
        //TODO Obvious duplicate of this object mapper registering modules, check it
        //TODO Maybe I don't need all of 3 modules, look here https://stackoverflow.com/questions/27952472/serialize-deserialize-java-8-java-time-with-jackson-json-mapper
        EventAddedEvent eventAddedEvent;
        try {
            eventAddedEvent = objectMapper.readValue(domainEventAsString, EventAddedEvent.class);
            eventAddedEvent.getHosts().forEach(hostId -> {
                gate.dispatch(MakeUserHostOfEvent
                        .builder()
                        .eventId(eventAddedEvent.getEventId())
                        .userId(hostId)
                        .build());
            });

        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }

    }
}
