package com.eventify.webscraper.domain;

import org.jsoup.nodes.Document;

public interface EventWebScraper {
    EventScraped scrapEvent(String linkToDocument);
}
