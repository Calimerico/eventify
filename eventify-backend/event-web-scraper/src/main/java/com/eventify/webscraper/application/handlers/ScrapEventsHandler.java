package com.eventify.webscraper.application.handlers;

import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.webscraper.application.commands.ScrapEvents;
import com.eventify.webscraper.domain.EventsWebScraper;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;
import java.util.List;

import static com.eventify.shared.kafka.Topic.EVENTS_TOPIC;

/**
 * Created by spasoje on 30-Nov-18.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class ScrapEventsHandler implements CommandHandler<ScrapEvents,Void> {

    private final KafkaEventProducer kafkaEventProducer;
    private final List<EventsWebScraper> eventsWebScrapers;

    @Override
    public Void handle(ScrapEvents scrapEvents) {
        eventsWebScrapers.forEach(eventsWebScraper -> kafkaEventProducer.send(eventsWebScraper.scrapEvents(), EVENTS_TOPIC));
        return null;
    }
}
