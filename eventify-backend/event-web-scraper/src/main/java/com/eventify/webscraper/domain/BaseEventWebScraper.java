package com.eventify.webscraper.domain;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BaseEventWebScraper implements EventWebScraper {

    protected abstract Document getBaseDocument();
    protected abstract String getBaseUrl();
    protected abstract List<String> getLinksToEvents(Document document);
    protected abstract String getEventName(Document document);
    protected abstract LocalDateTime getEventDateTime(Document document, DateTimeFormatter formatter);
    protected abstract DateTimeFormatter getEventDateTimeFormatter();
    protected abstract List<Integer> getPrices(Document document);
    protected abstract String getPlaceName(Document document);
    protected abstract String getProfilePicture(Document document);
    protected abstract EventType getEventType();
    protected abstract String getNextPageUrl(String currentUrl);

    @Override
    public EventsScraped scrapEvents() {
        List<EventScraped> scrapedEvents = new ArrayList<>();
        Document baseDocument = getBaseDocument();
        log.info("I am scraping url:" + baseDocument.baseUri());//todo check
        scrapEvents(scrapedEvents, baseDocument);
        return new EventsScraped(scrapedEvents);
    }

    private void scrapEvents(List<EventScraped> scrapedEvents, Document baseDocument) {
        List<String> linkOfEvents;
        linkOfEvents = getLinksToEvents(baseDocument);

        linkOfEvents.forEach(link -> {
            try {
                if (!link.contains("/venue/")) {
                    Document eventDocument = Jsoup.connect(link).get();
                    EventScraped eventScraped;
                    EventScraped.EventScrapedBuilder eventScrapedBuilder = EventScraped.builder().source(link);
                    eventScrapedBuilder.eventName(getEventName(eventDocument));
                    DateTimeFormatter formatter = getEventDateTimeFormatter();
                    eventScrapedBuilder.eventDateTime(getEventDateTime(eventDocument, formatter));
                    eventScrapedBuilder.prices(getPrices(eventDocument));
                    eventScrapedBuilder.placeId(getPlaceName(eventDocument));
                    eventScrapedBuilder.picture(getProfilePicture(eventDocument));
                    eventScrapedBuilder.eventType(getEventType());
                    eventScraped = eventScrapedBuilder.build();
                    log.debug("Event " + eventScraped + " is scraped!");
                    scrapedEvents.add(eventScraped);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        String nextPageUrl = getNextPageUrl(getBaseUrl());
        if (nextPageUrl != null) {
            try {
                scrapEvents(scrapedEvents, Jsoup.connect(nextPageUrl).get());
            } catch (IOException e) {
                e.printStackTrace();//todo
            }
        }
    }
}
