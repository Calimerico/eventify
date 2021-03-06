package com.eventify.webscraper.domain;

import com.eventify.shared.demo.EventType;
import com.eventify.webscraper.domain.events.EventScraped;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

@Slf4j
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
            if(eventDocument!=null){
                return getEventScraped(linkToDocument, eventDocument);
            }
           log.debug("Missing documment for link: {}",linkToDocument);
        } catch (IOException e) {
            e.printStackTrace();//todo
        }
        return null;
    }

    private EventScraped getEventScraped(String linkToDocument, Document eventDocument) {
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
