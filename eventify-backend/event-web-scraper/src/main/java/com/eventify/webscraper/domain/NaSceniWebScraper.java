package com.eventify.webscraper.domain;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Component
public class NaSceniWebScraper extends BaseEventWebScraper {

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
        return "https://nasceni.tickets.rs/event/category/pozoriste-1";
    }

    @Override
    protected String getDescription(Document document) {
        return null;//todo
    }

    @Override
    protected Set<String> getLinksToEvents(Document document) {
        return document.select(".infoContent > a")
                .stream()
                .map(element -> element.attr("href"))
                .filter(url -> !url.contains("/value"))
                .collect(Collectors.toSet());
    }

    @Override
    protected String getEventName(Document document) {
        return document.select(".programLarge h1").text();
    }

    @Override
    protected LocalDateTime getEventDateTime(Document document, DateTimeFormatter formatter) {
        String datetime = document.select(".ticketTime").attr("datetime");
        if (datetime == null || datetime.equals("")) {
            return null;
        }
        return LocalDateTime.parse(datetime, formatter);
    }

    @Override
    protected DateTimeFormatter getEventDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    protected List<Integer> getPrices(Document document) {
        List<Integer> prices = new ArrayList<>();
        for (Element element : document.select(".price")) {
            if (element.text().contains("RSD")) {
                int price = Integer.parseInt(element.text().replace(" ", "").split("\\.")[0]);
                if (price > 0) {//TODO HARDCODED We maybe need alerting module for this situations
                    prices.add(price);
                }
            }
        }
        return prices;
    }

    @Override
    protected String getPlaceName(Document document) {
        Elements place = document.select(".placeContainer a");
        if (place != null){
            return place.text();
        }
        return null;
    }

    @Override
    protected String getProfilePicture(Document document) {
        Elements pictureElement = document.select(".programLarge img");
        if (pictureElement != null) {
            return pictureElement.attr("src");
        }
        return null;
    }

    @Override
    protected EventType getEventType() {
        return EventType.THEATER;
    }//todo

    @Override
    protected String getNextPageUrl(String currentUrl) {
        Element nextPageButton = null;
        try {
            nextPageButton = Jsoup.connect(currentUrl).get().selectFirst("[rel=\"next\"]");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (nextPageButton != null) {
            return "https://nasceni.tickets.rs" + nextPageButton.attr("href");
        }
        return null;
    }
}