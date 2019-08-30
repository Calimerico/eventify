package com.eventify.events.infrastructure;

import com.eventify.config.kafka.KafkaStreams;
import com.eventify.events.api.msg.EventsScraped;
import com.eventify.events.application.commands.CreateEvent;
import com.eventify.events.application.commands.CreateEvents;
import com.eventify.shared.demo.Gate;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by spasoje on 23-Nov-18.
 */
@Service
@RequiredArgsConstructor
public class Consumer {//TODO Rename

    private final Gate gate;

    @StreamListener(KafkaStreams.INPUT)
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
                    .hosts(new HashSet<>(eventScraped.getEventHostIds()))//todo
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
}
