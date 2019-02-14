package com.eventify.webscraper.domain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Component
public class NaSceniWebScraper implements EventWebScraper {

    @Override
    public EventsScraped scrapEvents() {
        Elements linkOfEvents;
        List<EventScraped> scrapedList = new ArrayList<>();
        try {
            linkOfEvents = Jsoup.connect("https://nasceni.tickets.rs/event/category/pozoriste-1").get().select(".infoContent > a");

            linkOfEvents.forEach(link -> {
                EventScraped eventScraped;
                EventScraped.EventScrapedBuilder eventScrapedBuilder = EventScraped.builder().source(link.attr("href"));
                try {
                    Document eventDocument = Jsoup.connect(link.attr("href")).get();
                    String eventName = eventDocument.select(".programLarge h1").text();
                    eventScrapedBuilder.eventName(eventName);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime datetime = LocalDateTime.parse(eventDocument.select(".ticketTime").attr("datetime"), formatter);
                    eventScrapedBuilder.eventDateTime(datetime);
//                    ArrayList<Price> prices = new ArrayList<>();//TODO Implement this!!
//                    Elements pricesAndDescriptions = eventDocument.select(".ticketBoxElem");
//                    pricesAndDescriptions.forEach( priceAndDescription -> {
//                        Price price = new Price();
//                        priceAndDescription.select("div").stream().filter(priceOrSeatDescription -> "price".equals(priceOrSeatDescription.className())).forEach( priceElement -> {
//                            //Replace all method is added for case where price is 1 000. Purpose is to strip whitespace
//                            price.setPriceAmmount(Integer.parseInt(priceElement.text().substring(0, priceElement.text().length() - 7).replaceAll("\\s+","")));
//                        });
//                        priceAndDescription.select("div")
//                                .stream()
//                                .filter(priceOrSeatDescription -> !"price".equals(priceOrSeatDescription.className()) &&
//                                        priceOrSeatDescription.text() != null &&
//                                        !priceOrSeatDescription.text().equals("") &&
//                                        isDigit(priceOrSeatDescription.text().charAt(0)) &&
//                                        !priceOrSeatDescription.text().endsWith("RSD"))
//                                .forEach( priceElement -> {
//                                    //Replace all method is added for case where price is 1 000. Purpose is to strip whitespace
//                                    price.setSeatDescription(priceElement.text().split("RSD")[1].trim());
//                                });
//                        prices.add(price);
//                    });
//                    eventScrapedBuilder.setPrices(prices);

                    Elements place = eventDocument.select(".placeContainer a");
                    if (place != null){
                        eventScrapedBuilder.placeId(place.text());
                    }
                    Elements pictureElement = eventDocument.select(".programLarge img");
                    if (pictureElement != null) {
                        eventScrapedBuilder.picture(pictureElement.attr("src"));
                    }
                    eventScrapedBuilder.eventType("theater");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                eventScraped = eventScrapedBuilder.build();
                scrapedList.add(eventScraped);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EventsScraped.builder().eventsScraped(scrapedList).build();
    }
}
