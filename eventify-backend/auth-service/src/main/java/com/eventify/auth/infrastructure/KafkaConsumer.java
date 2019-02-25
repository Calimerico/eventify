package com.eventify.auth.infrastructure;

import com.eventify.KafkaStreams;
import com.eventify.auth.api.msg.EventAddedEvent;
import com.eventify.auth.application.commands.MakeUserHostOfEvent;
import com.eventify.shared.demo.Gate;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

/**
 * Created by spasoje on 20-Dec-18.
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final Gate gate;

    @StreamListener(KafkaStreams.INPUT)//TODO rename method
    public void handleEventsScraped(@Payload EventAddedEvent eventAddedEvent) {
        emptyIfNull(eventAddedEvent.getHosts()).forEach(hostId -> {
            gate.dispatch(MakeUserHostOfEvent
                    .builder()
                    .eventId(eventAddedEvent.getEventId())
                    .userId(hostId)
                    .build());
        });
    }


}
