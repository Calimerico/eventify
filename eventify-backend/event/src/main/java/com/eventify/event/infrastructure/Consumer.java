package com.eventify.event.infrastructure;

import com.eventify.event.api.msg.EventHostConfirmed;
import com.eventify.event.api.msg.EventsScraped;
import com.eventify.event.application.commands.CreateEvent;
import com.eventify.event.application.commands.CreateEvents;
import com.eventify.shared.demo.Gate;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.eventify.shared.kafka.KafkaStreams.EVENTS_TOPIC_INPUT_CHANNEL;

/**
 * Created by spasoje on 23-Nov-18.
 */
@Service
@RequiredArgsConstructor
public class Consumer {//TODO Rename

    private final Gate gate;

    @StreamListener(condition = "headers['eventType'] == 'EventsScraped' ", value = EVENTS_TOPIC_INPUT_CHANNEL)
    public void handleEventsScrapedEvent(@Payload EventsScraped eventsScraped) {
        List<CreateEvent> createEvents = new ArrayList<>();
        eventsScraped.getEventsScraped().forEach(eventScraped -> {
            createEvents.add(CreateEvent
                    .builder()
                    .eventName(eventScraped.getEventName())
                    .eventDateTime(eventScraped.getEventDateTime())
                    .eventType(eventScraped.getEventType())
                    .source(eventScraped.getSource())
                    .profilePicture(eventScraped.getPicture())
                    .description(eventScraped.getDescription())
                    .hosts(eventScraped.getEventHostIds())
//                    .placeId(eventScraped.getPlaceId())//todo
                    .build()
            );
        });
        gate.dispatch(CreateEvents
                .builder()
                .events(createEvents)
                .build()
        );
    }
    @StreamListener(condition = "headers['eventType'] == 'EventHostConfirmed' ", value = EVENTS_TOPIC_INPUT_CHANNEL)
    public void handleEventHostConfirmed(@Payload EventHostConfirmed eventHostConfirmed) {
        gate.dispatch(com.eventify.event.application.commands.EventHostConfirmed
                .builder()
                .eventId(eventHostConfirmed.getEventId())
                .hostId(eventHostConfirmed.getHostId())
                .build()
        );
    }
}
