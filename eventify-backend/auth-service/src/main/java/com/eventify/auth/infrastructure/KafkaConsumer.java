package com.eventify.auth.infrastructure;

import com.eventify.auth.api.msg.EventAddedEvent;
import com.eventify.auth.api.msg.EventDeletedEvent;
import com.eventify.auth.application.commands.MakeUserHostOfEvent;
import com.eventify.auth.application.commands.RemoveEventFromUsers;
import com.eventify.shared.demo.Gate;
import com.eventify.unconfirmedeventsonhost.application.commands.UnconfirmedEventOnHost;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.eventify.shared.kafka.KafkaStreams.EVENTS_TOPIC_INPUT_CHANNEL;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

/**
 * Created by spasoje on 20-Dec-18.
 */
@Service
@RequiredArgsConstructor
//todo maybe change class name
public class KafkaConsumer {

    private final Gate gate;

    @StreamListener(condition = "headers['eventType'] == 'EventAddedEvent' ", value = EVENTS_TOPIC_INPUT_CHANNEL)
    public void handleEventAddedEvent(@Payload EventAddedEvent eventAddedEvent) {
        emptyIfNull(eventAddedEvent.getConfirmedHosts()).forEach(confirmedHostId -> {
            gate.dispatch(MakeUserHostOfEvent
                    .builder()
                    .eventId(eventAddedEvent.getEventId())
                    .userId(confirmedHostId)
                    .build());
        });
        emptyIfNull(eventAddedEvent.getUnconfirmedHosts()).forEach(unconfirmedHostId -> {
            gate.dispatch(UnconfirmedEventOnHost
                    .builder()
                    .eventId(eventAddedEvent.getEventId())
                    .hostId(unconfirmedHostId)
                    .build());
        });
    }

    @StreamListener(condition = "headers['eventType'] == 'EventDeletedEvent' ", value = EVENTS_TOPIC_INPUT_CHANNEL)
    public void handleEventDeletedEvent(@Payload EventDeletedEvent eventDeletedEvent) {
        gate.dispatch(RemoveEventFromUsers
                .builder()
                .eventId(eventDeletedEvent.getEventId())
                .confirmedHosts(eventDeletedEvent.getConfirmedHosts())
                .unconfirmedHosts(eventDeletedEvent.getUnconfirmedHosts())
                .build()
        );
    }


}
