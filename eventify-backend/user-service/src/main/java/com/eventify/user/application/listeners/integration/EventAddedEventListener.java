package com.eventify.user.application.listeners.integration;

import com.eventify.eventsonhost.application.commands.UnconfirmEventHost;
import com.eventify.shared.demo.Gate;
import com.eventify.shared.demo.IntegrationEventListener;
import com.eventify.user.api.integration.events.input.EventAddedEvent;
import com.eventify.user.application.commands.MakeUserHostOfEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.eventify.shared.kafka.KafkaStreams.EVENTS_TOPIC_INPUT_CHANNEL;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Component
@RequiredArgsConstructor
public class EventAddedEventListener implements IntegrationEventListener<EventAddedEvent> {

    private final Gate gate;

    @Override
    @StreamListener(condition = "headers['eventType'] == 'EventAddedEvent' ", value = EVENTS_TOPIC_INPUT_CHANNEL)
    public void handle(@Payload EventAddedEvent eventAddedEvent) {
        emptyIfNull(eventAddedEvent.getConfirmedHosts()).forEach(confirmedHostId -> {
            gate.dispatch(MakeUserHostOfEvent
                    .builder()
                    .eventId(eventAddedEvent.getEventId())
                    .userId(confirmedHostId)
                    .build());
        });
        emptyIfNull(eventAddedEvent.getUnconfirmedHosts()).forEach(unconfirmedHostId -> {
            gate.dispatch(UnconfirmEventHost
                    .builder()
                    .eventId(eventAddedEvent.getEventId())
                    .hostId(unconfirmedHostId)
                    .build());
        });
    }
}
