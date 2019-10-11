package com.eventify.user.application.listeners.integration;

import com.eventify.shared.demo.Gate;
import com.eventify.shared.demo.IntegrationEventListener;
import com.eventify.user.api.integration.events.input.EventDeletedEvent;
import com.eventify.user.application.commands.RemoveEventFromUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.eventify.shared.kafka.KafkaStreams.EVENTS_TOPIC_INPUT_CHANNEL;

@Component
@RequiredArgsConstructor
public class EventDeletedEventListener implements IntegrationEventListener<EventDeletedEvent> {

    private final Gate gate;

    @Override
    @StreamListener(condition = "headers['eventType'] == 'EventDeletedEvent' ", value = EVENTS_TOPIC_INPUT_CHANNEL)
    public void handle(@Payload EventDeletedEvent eventDeletedEvent) {
        gate.dispatch(RemoveEventFromUsers
                .builder()
                .eventId(eventDeletedEvent.getEventId())
                .confirmedHosts(eventDeletedEvent.getConfirmedHosts())
                .unconfirmedHosts(eventDeletedEvent.getUnconfirmedHosts())
                .build()
        );
    }
}
