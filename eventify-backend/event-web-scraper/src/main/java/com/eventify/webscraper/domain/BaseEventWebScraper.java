package com.eventify.webscraper.domain;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseEventWebScraper implements EventWebScraper {

    protected abstract String getBaseUrl();
    protected abstract List<String> getLinksToEvents(String url);
    protected abstract String getEventName();
    protected abstract LocalDateTime getEventDateTime(DateTimeFormatter formatter);
    protected abstract DateTimeFormatter getEventDateTimeFormatter();
    protected abstract List<Integer> getPrices();
    protected abstract String getPlaceName();
    protected abstract String getProfilePicture();
    protected abstract String getEventType();
    protected abstract String getNextPageUrl();

    @Override
    public EventsScraped scrapEvents() {
        List<EventScraped> scrapedEvents = new ArrayList<>();
        String baseUrl = getBaseUrl();
        scrapEvents(scrapedEvents, baseUrl);
        return new EventsScraped(scrapedEvents);
    }

    private void scrapEvents(List<EventScraped> scrapedEvents, String url) {
        List<String> linkOfEvents;
        linkOfEvents = getLinksToEvents(url);

        linkOfEvents.forEach(link -> {
            EventScraped eventScraped;
            EventScraped.EventScrapedBuilder eventScrapedBuilder = EventScraped.builder().source(link);
            eventScrapedBuilder.eventName(getEventName());
            DateTimeFormatter formatter = getEventDateTimeFormatter();
            eventScrapedBuilder.eventDateTime(getEventDateTime(formatter));
            eventScrapedBuilder.prices(getPrices());
            eventScrapedBuilder.placeId(getPlaceName());
            eventScrapedBuilder.picture(getProfilePicture());
            eventScrapedBuilder.eventType(getEventType());
            eventScraped = eventScrapedBuilder.build();
            scrapedEvents.add(eventScraped);
        });
        if (getNextPageUrl() != null) {
            scrapEvents(scrapedEvents, getNextPageUrl());
        }
    }
}
