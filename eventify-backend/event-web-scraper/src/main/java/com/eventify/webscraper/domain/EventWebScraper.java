package com.eventify.webscraper.domain;

import com.eventify.webscraper.domain.events.EventScraped;

public interface EventWebScraper {
    EventScraped scrapEvent(String linkToDocument);
}
