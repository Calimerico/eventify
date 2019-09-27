package com.eventify.webscraper.domain;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.eventify.webscraper.domain.EventWebScraperFactory.create;

@Slf4j
public abstract class BaseEventsWebScraper implements EventsWebScraper {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    protected abstract Document getBaseDocument();
    protected abstract ScraperType getScraperType();
    protected abstract Set<String> getLinksToEvents(Document document);
    protected abstract String getBaseUrl();
    protected abstract String getNextPageUrl(String currentUrl);

    @Override
    public EventsScraped scrapEvents() {
        List<EventScraped> scrapedEvents = Collections.synchronizedList(new LinkedList<>());
        Document baseDocument = getBaseDocument();
        log.info("I am scraping url:" + baseDocument.baseUri());//todo check
        scrapEvents(scrapedEvents, baseDocument);
        try {
            executorService.shutdown();
            executorService.awaitTermination(40, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();//todo
        }
        return new EventsScraped(scrapedEvents);
    }

    private void scrapEvents(List<EventScraped> scrapedEvents, Document baseDocument) {
        Set<String> linkOfEvents;
        linkOfEvents = getLinksToEvents(baseDocument);

        linkOfEvents.forEach(link -> {
            EventWebScraperThread t = new EventWebScraperThread(scrapedEvents, link, create(getScraperType()));
            executorService.execute(t);


        });
        String nextPageUrl = getNextPageUrl(getBaseUrl());
        if (nextPageUrl != null) {
            try {
                scrapEvents(scrapedEvents, Jsoup.connect(nextPageUrl).get());
            } catch (IOException e) {
                e.printStackTrace();//todo
            }
        }
    }
}
