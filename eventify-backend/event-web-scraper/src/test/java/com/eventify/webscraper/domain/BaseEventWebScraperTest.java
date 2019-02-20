package com.eventify.webscraper.domain;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by spasoje on 20-Feb-19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class BaseEventWebScraperTest {

    @Autowired
    private NaSceniWebScraper naSceniWebScraper;

    @Test
    public void name() throws Exception {
        EventsScraped eventsScraped = naSceniWebScraper.scrapEvents();
        Assertions.assertThat(eventsScraped).isNotNull();
    }
}
