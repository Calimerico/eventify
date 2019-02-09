package com.eventify.events.infrastructure;

import com.eventify.events.api.msg.EventsScraped;
import com.eventify.events.domain.Event;
import com.eventify.events.domain.EventFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by spasoje on 23-Nov-18.
 */
@Service
@RequiredArgsConstructor
public class Consumer {//TODO Rename

    private final ObjectMapper objectMapper;
    private final EventRepository eventRepository;

    @KafkaListener(topics = "cqrs")
    //TODO This listener is invoked twice instead of once every time, check kafka messages!
    public void receiveTopic(ConsumerRecord<?, String> consumerRecord) {
        //TODO Guess this assembling should be removed too
        String domainEventAsString = consumerRecord.value();
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
                .map(EventFactory::create).collect(Collectors.toList());
        eventRepository.saveAll(events);
    }
}
