package com.eventify.webscraper.domain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class KonferencijeWebScraper extends BaseEventWebScraper {

    @Override
    protected Document getBaseDocument() {
        try {
            return Jsoup.connect(getBaseUrl()).get();
        } catch (IOException e) {
            e.printStackTrace();//todo
        }
        return null;
    }

    @Override
    protected String getBaseUrl() {
        return "https://konferencije.rs/sr/doga%C4%91aji";
    }

    @Override
    protected String getDescription(Document document) {
        return document.select("p[itemProp=description]").text();
    }

    @Override
    protected Set<String> getLinksToEvents(Document document) {
        return document.select("[itemProp=url]")
                .stream()
                .map(element -> element.attr("content"))
                .filter(url -> !url.equals("https://konferencije.rs/sr/doga%C4%91aji"))
                .collect(Collectors.toSet());
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

    @Override
    protected String getNextPageUrl(String currentUrl) {
        return null;
    }
}
