package com.eventify.event.application.listeners.integration;

import com.eventify.event.api.integration.events.input.EventHostConfirmed;
import com.eventify.shared.demo.Gate;
import com.eventify.shared.demo.IntegrationEventListener;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.eventify.shared.kafka.KafkaStreams.EVENTS_TOPIC_INPUT_CHANNEL;

@Component
@RequiredArgsConstructor
public class EventHostConfirmedListener implements IntegrationEventListener<EventHostConfirmed> {

    private final Gate gate;

    @Override
    @StreamListener(condition = "headers['eventType'] == 'EventHostConfirmed' ", value = EVENTS_TOPIC_INPUT_CHANNEL)
    public void handle(@Payload EventHostConfirmed eventHostConfirmed) {
        gate.dispatch(com.eventify.event.application.commands.EventHostConfirmed
                .builder()
                .eventId(eventHostConfirmed.getEventId())
                .hostId(eventHostConfirmed.getHostId())
                .build()
        );
    }
}
