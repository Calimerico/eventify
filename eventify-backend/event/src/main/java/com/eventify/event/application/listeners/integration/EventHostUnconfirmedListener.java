package com.eventify.event.application.listeners.integration;

import com.eventify.event.api.integration.events.input.EventHostUnconfirmed;
import com.eventify.shared.demo.Gate;
import com.eventify.shared.demo.IntegrationEventListener;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.eventify.shared.kafka.KafkaStreams.EVENTS_TOPIC_INPUT_CHANNEL;

@Component
@RequiredArgsConstructor
public class EventHostUnconfirmedListener implements IntegrationEventListener<EventHostUnconfirmed> {

    private final Gate gate;

    @Override
    @StreamListener(condition = "headers['eventType'] == 'EventHostUnconfirmed' ", value = EVENTS_TOPIC_INPUT_CHANNEL)
    public void handle(@Payload EventHostUnconfirmed eventHostUnconfirmed) {
        gate.dispatch(com.eventify.event.application.commands.EventHostUnconfirmed
                .builder()
                .eventId(eventHostUnconfirmed.getEventId())
                .hostId(eventHostUnconfirmed.getHostId())
                .build()
        );
    }
}
