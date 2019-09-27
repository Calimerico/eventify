package com.eventify.webscraper.domain.nasceni;

import com.eventify.webscraper.domain.BaseEventsWebScraper;
import com.eventify.webscraper.domain.ScraperType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Component
public class NaSceniEventsWebScraper extends BaseEventsWebScraper {

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
    protected Set<String> getLinksToEvents(Document document) {
        return document.select(".infoContent > a")
                .stream()
                .map(element -> element.attr("href"))
                .filter(url -> !url.contains("/value"))
                .collect(Collectors.toSet());
    }

    @Override
    protected ScraperType getScraperType() {
        return ScraperType.NA_SCENI;
    }

    @Override
    protected String getBaseUrl() {
        return "https://nasceni.tickets.rs/event/category/pozoriste-1";
    }



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