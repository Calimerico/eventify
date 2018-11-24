package com;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by spasoje on 21-Nov-18.
 */
public class NaSceniWebScraper implements EventWebScraper {

    @Override
    public List<Event> scrapEvents() {
        Elements linkOfEvents;
        List<Event> events = new ArrayList<>();
        try {
            linkOfEvents = Jsoup.connect("https://nasceni.tickets.rs/event/category/pozoriste-1").get().select(".infoContent > a");

            linkOfEvents.forEach(link -> {
                Event event = new Event();
                event.setSource(link.attr("href"));
                try {
                    Document eventDocument = Jsoup.connect(link.attr("href")).get();
                    event.setEventName(eventDocument.select(".programLarge h1").text());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime datetime = LocalDateTime.parse(eventDocument.select(".ticketTime").attr("datetime"), formatter);
                    event.setEventDateAndTime(ZonedDateTime.of(datetime, ZoneId.of("Europe/Paris")));
                    ArrayList<Price> prices = new ArrayList<>();
                    Elements pricesAndDescriptions = eventDocument.select(".ticketBoxElem");
                    pricesAndDescriptions.forEach( priceAndDescription -> {
                        Price price = new Price();
                        priceAndDescription.select("div").stream().filter(priceOrSeatDescription -> "price".equals(priceOrSeatDescription.className())).forEach( priceElement -> {
                            //Replace all method is added for case where price is 1 000. Purpose is to strip whitespace
                            price.setPriceAmmount(Integer.parseInt(priceElement.text().substring(0, priceElement.text().length() - 7).replaceAll("\\s+","")));
                        });
                        priceAndDescription.select("div").stream().filter(priceOrSeatDescription -> !"price".equals(priceOrSeatDescription.className()) && Character.isDigit(priceOrSeatDescription.text().charAt(0)) && !priceOrSeatDescription.text().endsWith("RSD")).forEach( priceElement -> {
                            //Replace all method is added for case where price is 1 000. Purpose is to strip whitespace
                            price.setSeatDescription(priceElement.text().split("RSD")[1].trim());
                        });
                        prices.add(price);
                    });
                    event.setPrices(prices);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                events.add(event);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return events;
    }
}
