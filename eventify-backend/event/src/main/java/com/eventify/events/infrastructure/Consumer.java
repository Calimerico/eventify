package com.eventify.events.infrastructure;

import com.eventify.KafkaStreams;
import com.eventify.events.api.msg.EventsScraped;
import com.eventify.events.domain.Event;
import com.eventify.events.domain.EventFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by spasoje on 23-Nov-18.
 */
@Service
@RequiredArgsConstructor
public class Consumer {//TODO Rename

    private final EventRepository eventRepository;//TODO Controller instead of repo?

    @StreamListener(KafkaStreams.INPUT)//TODO rename method
    public void handleEventsScrapedEvent(@Payload EventsScraped eventsScraped) {
        List<Event> events = eventsScraped
                .getEventsScraped()
                .stream()
                .map(EventFactory::create).collect(Collectors.toList());
        eventRepository.saveAll(events);
    }
}
