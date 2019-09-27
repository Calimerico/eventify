package com.eventify.webscraper.domain.konferencije;

import com.eventify.webscraper.domain.BaseEventsWebScraper;
import com.eventify.webscraper.domain.ScraperType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class KonferencijeEventsWebScraper extends BaseEventsWebScraper {

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
        return document.select("[itemProp=url]")
                .stream()
                .map(element -> element.attr("content"))
                .filter(url -> !url.equals("https://konferencije.rs/sr/doga%C4%91aji"))
                .collect(Collectors.toSet());
    }

    @Override
    protected ScraperType getScraperType() {
        return ScraperType.KONFERENCIJE;
    }

    @Override
    protected String getBaseUrl() {
        return "https://konferencije.rs/sr/doga%C4%91aji";
    }



    @Override
    protected String getNextPageUrl(String currentUrl) {
        return null;
    }
}
