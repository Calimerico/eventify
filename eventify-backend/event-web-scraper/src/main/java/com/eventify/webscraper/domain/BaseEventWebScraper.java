package com.eventify.webscraper.domain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseEventWebScraper implements EventWebScraper {

    protected abstract String getDescription(Document document);
    protected abstract String getEventName(Document document);
    protected abstract LocalDateTime getEventDateTime(Document document, DateTimeFormatter formatter);
    protected abstract DateTimeFormatter getEventDateTimeFormatter();
    protected abstract List<Integer> getPrices(Document document);
    protected abstract String getPlaceName(Document document);
    protected abstract String getProfilePicture(Document document);
    protected abstract EventType getEventType();

    @Override
    public EventScraped scrapEvent(String linkToDocument) {
        Document eventDocument = null;
        try {
            eventDocument = Jsoup.connect(linkToDocument).get();
        } catch (IOException e) {
            e.printStackTrace();//todo
        }
        String eventName = getEventName(eventDocument);
        DateTimeFormatter formatter = getEventDateTimeFormatter();
        LocalDateTime eventDateTime = getEventDateTime(eventDocument, formatter);
        if (eventName == null || eventDateTime == null) {
            return null;//this skips just one iteration, like continue in for loop todo!!!!!!!!
        }
        EventScraped.EventScrapedBuilder eventScrapedBuilder = EventScraped.builder().source(linkToDocument);
        eventScrapedBuilder.eventName(eventName);
        eventScrapedBuilder.eventDateTime(eventDateTime);
        eventScrapedBuilder.prices(new HashSet<>(getPrices(eventDocument)));
        eventScrapedBuilder.placeId(getPlaceName(eventDocument));
        eventScrapedBuilder.picture(getProfilePicture(eventDocument));
        eventScrapedBuilder.eventType(getEventType());
        return eventScrapedBuilder.build();
    }
}
