package com.eventify.event.application.listeners.integration;

import com.eventify.event.api.integration.events.input.EventsScraped;
import com.eventify.event.application.commands.CreateEvent;
import com.eventify.event.application.commands.CreateEvents;
import com.eventify.shared.demo.Gate;
import com.eventify.shared.demo.IntegrationEventListener;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.eventify.shared.kafka.KafkaStreams.EVENTS_TOPIC_INPUT_CHANNEL;

@Component
@RequiredArgsConstructor
public class EventsScrapedListener implements IntegrationEventListener<EventsScraped> {


    private final Gate gate;

    @Override
    @StreamListener(condition = "headers['eventType'] == 'EventsScraped' ", value = EVENTS_TOPIC_INPUT_CHANNEL)
    public void handle(@Payload EventsScraped eventsScraped) {
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
                            .createdDateTime(LocalDateTime.now())
//                            .createdBy()//todo created by scraper
//                          .placeId(eventScraped.getPlaceId())//todo
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
