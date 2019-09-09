package com.eventify.webscraper.application.handlers;

import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.shared.kafka.Topic;
import com.eventify.webscraper.application.commands.ScrapEvents;
import com.eventify.webscraper.domain.EventWebScraper;
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
    private final List<EventWebScraper> eventWebScrapers;

    @Override
    public Void handle(ScrapEvents scrapEvents) {
        eventWebScrapers.forEach(eventWebScraper -> kafkaEventProducer.send(eventWebScraper.scrapEvents(), EVENTS_TOPIC));
        return null;
    }
}
