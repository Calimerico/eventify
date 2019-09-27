package com.eventify.webscraper.domain;

import org.jsoup.nodes.Document;

import java.util.List;

public class EventWebScraperThread implements Runnable {
    private List<EventScraped> events;
    private String linkToDocument;
    private EventWebScraper eventWebScraper;

    public EventWebScraperThread(List<EventScraped> events, String linkToDocument, EventWebScraper eventWebScraper) {
        this.events = events;
        this.linkToDocument = linkToDocument;
        this.eventWebScraper = eventWebScraper;
    }

    @Override
    public void run() {
        events.add(eventWebScraper.scrapEvent(linkToDocument));
    }
}
