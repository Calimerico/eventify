package com.eventify.webscraper.domain;

import org.jsoup.nodes.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class KonferencijeEventWebScraper extends BaseEventWebScraper {
    @Override
    protected String getDescription(Document document) {
        return document.select("p[itemProp=description]").text();
    }



    @Override
    protected String getEventName(Document document) {
        return document.selectFirst(".hero div h1").text();
    }

    @Override
    protected LocalDateTime getEventDateTime(Document document, DateTimeFormatter formatter) {
        return LocalDateTime.parse(document.select(".description [itemProp=startDate]").attr("content"), getEventDateTimeFormatter());
    }

    @Override
    protected DateTimeFormatter getEventDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        //todo check that XXX at the end, time zone is bad!
    }

    @Override
    protected List<Integer> getPrices(Document document) {
        return new ArrayList<>();
    }

    @Override
    protected String getPlaceName(Document document) {
        return document.select(".venue-info h4").text();
    }

    @Override
    protected String getProfilePicture(Document document) {
        return null;
    }

    @Override
    protected EventType getEventType() {
        return null;
    }
}