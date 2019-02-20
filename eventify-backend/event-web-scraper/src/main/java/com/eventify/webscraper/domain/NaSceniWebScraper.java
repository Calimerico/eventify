package com.eventify.webscraper.domain;

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
import java.util.stream.Collectors;

import static java.lang.Character.isDigit;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Component
public class NaSceniWebScraper extends BaseEventWebScraper {

    @Override
    protected String getBaseUrl() {
        return "https://nasceni.tickets.rs/event/category/pozoriste-1";
    }

    @Override
    protected List<String> getLinksToEvents(String url) {
        try {
            return Jsoup.connect(url).get().select(".infoContent > a").stream().map(element -> element.attr("href")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();//TODO
        }
        return null;
    }

    @Override
    protected String getEventName(Document document) {
        return document.select(".programLarge h1").text();
    }

    @Override
    protected LocalDateTime getEventDateTime(Document document, DateTimeFormatter formatter) {
        return LocalDateTime.parse(document.select(".ticketTime").attr("datetime"), formatter);
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
    protected String getEventType() {
        return "theater";
    }

    @Override
    protected String getNextPageUrl() {
        Element nextPageButton = null;
        try {
            nextPageButton = Jsoup.connect(getBaseUrl()).get().selectFirst("[rel=\"next\"]");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (nextPageButton != null) {
            return "https://nasceni.tickets.rs" + nextPageButton.attr("href");
        }
        return null;
    }
}