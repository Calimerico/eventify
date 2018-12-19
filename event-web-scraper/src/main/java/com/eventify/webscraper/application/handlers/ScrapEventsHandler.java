package com.eventify.webscraper.application.handlers;

import com.eventify.webscraper.application.commands.ScrapEvents;
import com.eventify.webscraper.domain.EventWebScraper;
import com.eventify.webscraper.infrasturcture.KafkaEventProducer;
import com.eventifyshared.demo.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by spasoje on 30-Nov-18.
 */
@com.eventifyshared.net.CommandHandler
public class ScrapEventsHandler implements CommandHandler<ScrapEvents,Void> {

    @Autowired
    private KafkaEventProducer kafkaEventProducer;

    @Autowired
    private List<EventWebScraper> eventWebScrapers;

    @Override
    public Void handle(ScrapEvents scrapEvents) {
        eventWebScrapers.forEach(eventWebScraper -> kafkaEventProducer.send(eventWebScraper.scrapEvents()));
        return null;
    }
}
