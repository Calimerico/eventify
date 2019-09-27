package com.eventify.webscraper.domain;

public class EventWebScraperFactory {
    public static EventWebScraper create(ScraperType scraperType) {
        switch (scraperType) {
            case NA_SCENI:
                return new NaSceniEventWebScraper();
            case KONFERENCIJE:
                return new KonferencijeEventWebScraper();
            default:
                throw new IllegalArgumentException("Nonexistent scraper of type " + scraperType + " !");
        }
    }
}
