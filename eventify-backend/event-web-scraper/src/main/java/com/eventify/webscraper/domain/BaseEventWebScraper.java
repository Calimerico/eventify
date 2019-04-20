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

    protected abstract String getBaseUrl();
    protected abstract List<String> getLinksToEvents(String url);
    protected abstract String getEventName(Document document);
    protected abstract LocalDateTime getEventDateTime(Document document, DateTimeFormatter formatter);
    protected abstract DateTimeFormatter getEventDateTimeFormatter();
    protected abstract List<Integer> getPrices(Document document);
    protected abstract String getPlaceName(Document document);
    protected abstract String getProfilePicture(Document document);
    protected abstract String getEventType();
    protected abstract String getNextPageUrl(String currentUrl);

    @Override
    public EventsScraped scrapEvents() {
        List<EventScraped> scrapedEvents = new ArrayList<>();
        String baseUrl = getBaseUrl();
        log.info("I am scraping url:" + baseUrl);
        scrapEvents(scrapedEvents, baseUrl);
        return new EventsScraped(scrapedEvents);
    }

    private void scrapEvents(List<EventScraped> scrapedEvents, String url) {
        List<String> linkOfEvents;
        linkOfEvents = getLinksToEvents(url);

        linkOfEvents.forEach(link -> {
            try {
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
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        //TODO For development purposes is commented
//        String nextPageUrl = getNextPageUrl(url);
//        if (nextPageUrl != null) {
//            scrapEvents(scrapedEvents, nextPageUrl);
//        }
    }
}
