package com.eventify.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by spasoje on 23-Nov-18.
 */
@Service
public class Consumer {

    @Autowired
    private EventRepository eventRepository;

    @KafkaListener(topics = "cqrs")
    public void receiveTopic(ConsumerRecord<?, String> consumerRecord) {
        //TODO Guess this assembling should be removed too
        String domainEventAsString = consumerRecord.value();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule()); // new module, NOT JSR310Module
        //TODO Maybe I dont need all of 3 modules, look here https://stackoverflow.com/questions/27952472/serialize-deserialize-java-8-java-time-with-jackson-json-mapper
        EventsScraped eventsScraped = null;
        try {
            eventsScraped = objectMapper.readValue(domainEventAsString, EventsScraped.class);
        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }
        List<Event> events = eventsScraped
                .getEventsScraped()
                .stream()
                .map(EventBuilder::create).collect(Collectors.toList());
        eventRepository.saveAll(events);
    }
}
