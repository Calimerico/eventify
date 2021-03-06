package com.eventify.webscraper.domain;

import com.eventify.webscraper.domain.konferencije.KonferencijeEventWebScraper;
import com.eventify.webscraper.domain.nasceni.NaSceniEventWebScraper;

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
