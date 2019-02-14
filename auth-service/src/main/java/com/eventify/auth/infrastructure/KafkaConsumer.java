package com.eventify.auth.infrastructure;

import com.eventify.auth.api.msg.EventAddedEvent;
import com.eventify.auth.application.commands.MakeUserHostOfEvent;
import com.eventify.shared.demo.Gate;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "cqrs1")
    public void receiveTopic(ConsumerRecord<?, String> consumerRecord) {
        String domainEventAsString = consumerRecord.value();
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
