package com.eventify.webscraper.domain;


import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;

import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class KonferencijeWebScraperTest {

    @SpyBean
    private KonferencijeWebScraper konferencijeWebScraper;

    @Before
    public void setUp() throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource("konferencije.html");
        File file = new File(url.getPath());
        when(konferencijeWebScraper.getBaseDocument()).thenReturn(Jsoup.parse(file,"UTF-8"));
        when(konferencijeWebScraper.getNextPageUrl("https://konferencije.rs/sr/doga%C4%91aji")).thenReturn(null);
    }

    @Test
    public void name() {
        EventsScraped eventsScraped = konferencijeWebScraper.scrapEvents();
        Assertions.assertThat(eventsScraped).isNotNull();
        Assertions.assertThat(eventsScraped.getEventsScraped().size()).isEqualTo(30);
    }
}
