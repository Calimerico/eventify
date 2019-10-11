package com.eventify.webscraper.domain.konferencije;


import com.eventify.webscraper.domain.events.EventsScraped;
import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.junit.Before;
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
public class KonferencijeEventsWebScraperTest {

    @SpyBean
    private KonferencijeEventsWebScraper konferencijeEventsWebScraper;

    @Before
    public void setUp() throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource("konferencije.html");
        File file = new File(url.getPath());
        when(konferencijeEventsWebScraper.getBaseDocument()).thenReturn(Jsoup.parse(file,"UTF-8"));
        when(konferencijeEventsWebScraper.getNextPageUrl("https://konferencije.rs/sr/doga%C4%91aji")).thenReturn(null);
    }

    @Test
    public void name() {
        long startTime = System.currentTimeMillis();
        EventsScraped eventsScraped = konferencijeEventsWebScraper.scrapEvents();
        Assertions.assertThat(eventsScraped).isNotNull();
        Assertions.assertThat(eventsScraped.getEventsScraped().size()).isEqualTo(99);
        System.out.println("vreme je " + (System.currentTimeMillis() - startTime));
    }
}
