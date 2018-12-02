package com;

import com.example.demo.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by spasoje on 30-Nov-18.
 */
@com.net.CommandHandler
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
