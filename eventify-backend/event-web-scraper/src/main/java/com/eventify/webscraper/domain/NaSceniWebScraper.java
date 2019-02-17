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

import static java.lang.Character.isDigit;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Component
public class NaSceniWebScraper implements EventWebScraper {

    @Override
    public EventsScraped scrapEvents() {
        List<EventScraped> scrapedEvents = new ArrayList<>();
        String baseUrl = "https://nasceni.tickets.rs/event/category/pozoriste-1";
        try {
            scrapEvents(scrapedEvents, baseUrl);
        } catch (IOException e) {
            e.printStackTrace();//TODO
        }
        return new EventsScraped(scrapedEvents);
    }

    public static void scrapEvents(List<EventScraped> scrapedEvents, String url) throws IOException {
        Elements linkOfEvents;
        linkOfEvents = Jsoup.connect(url).get().select(".infoContent > a");

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
                List<Ticket> tickets = new ArrayList<>();
                for (Element element : eventDocument.select(".price")) {
                    if (element.text().contains("RSD")) {
                        Ticket ticket = new Ticket(Integer.parseInt(element.text().replace(" ","").split("\\.")[0]));
                        if (ticket.getPrice() > 0) {//TODO HARDCODED We maybe need alerting module for this situations
                            tickets.add(ticket);
                        }
                    }
                }
                eventScrapedBuilder.tickets(tickets);

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
            scrapedEvents.add(eventScraped);
        });
        Element nextPageButton = Jsoup.connect(url).get().selectFirst("[rel=\"next\"]");
        if (nextPageButton != null) {
            scrapEvents(scrapedEvents, "https://nasceni.tickets.rs" + nextPageButton.attr("href"));
        }
    }
}