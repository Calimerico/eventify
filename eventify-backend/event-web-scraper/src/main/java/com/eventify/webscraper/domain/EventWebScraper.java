package com.eventify.webscraper.domain;

/**
 * Created by spasoje on 21-Nov-18.
 */
public interface EventWebScraper {
    EventsScraped scrapEvents();//dont implement this like Queue since we want to send one kafka message for all events, not 500 messages for 500 messages
}
