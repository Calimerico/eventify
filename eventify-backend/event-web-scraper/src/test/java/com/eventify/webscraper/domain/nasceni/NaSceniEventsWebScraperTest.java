package com.eventify.webscraper.domain.nasceni;

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

/**
 * Created by spasoje on 20-Feb-19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class NaSceniEventsWebScraperTest {

    @SpyBean
    private NaSceniEventsWebScraper naSceniEventsWebScraper;

    @Before
    public void setUp() throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource("nasceni.html");
        File file = new File(url.getPath());
        when(naSceniEventsWebScraper.getBaseDocument()).thenReturn(Jsoup.parse(file,"UTF-8"));
        when(naSceniEventsWebScraper.getNextPageUrl("https://nasceni.tickets.rs/event/category/pozoriste-1")).thenReturn(null);
    }

    @Test
    public void name() throws Exception {
        EventsScraped eventsScraped = naSceniEventsWebScraper.scrapEvents();
        Assertions.assertThat(eventsScraped).isNotNull();
//        Assertions.assertThat(eventsScraped.getEventsScraped().size()).isEqualTo(30);//todo
//        Assertions.assertThat(new HashSet<>(eventsScraped.getEventsScraped())).contains(
//                EventScraped
//                        .builder()
//                        .eventName("HOTEL 88")
//                        .eventType("THEATER")
//                        .placeId("Hotel Mona Miladina Pećinara 26")
//                        .picture("https://pb2eu.interticket.com/imgs/system-20/program/000/001/084/hotel-88-474-279-8495.jpg")
//                        .eventDateTime(LocalDateTime.of(LocalDate.of(2019, Month.AUGUST, 16), LocalTime.of(21,0)))
//                        .source("https://nasceni.tickets.rs/program/hotel-88-1208")
//                        .prices(Collections.singletonList(1000))
//                        .build(),
//                EventScraped
//                        .builder()
//                        .eventName("HOTEL 88")
//                        .eventType("THEATER")
//                        .placeId("Letnja pozornica Palić Park Heroja bb, 24413 Palić")
//                        .picture("https://pb2eu.interticket.com/imgs/system-20/program/000/001/084/hotel-88-474-279-8495.jpg")
//                        .eventDateTime(LocalDateTime.of(LocalDate.of(2019, Month.AUGUST, 18), LocalTime.of(20,30)))
//                        .source("https://nasceni.tickets.rs/program/hotel-88-1084")
//                        .prices(Collections.singletonList(900))
//                        .build(),
//                EventScraped
//                        .builder()
//                        .eventName("HOTEL 88")
//                        .eventType("THEATER")
//                        .placeId("Amfitatar \"Danilo Bata Stojkovic\"-Letnja Pozornica Save Kovačevića, 36210 Vrnjačka Banja")
//                        .picture("https://pb2eu.interticket.com/imgs/system-20/program/000/001/084/hotel-88-474-279-8495.jpg")
//                        .eventDateTime(LocalDateTime.of(LocalDate.of(2019, Month.AUGUST, 20), LocalTime.of(21,0)))
//                        .source("https://nasceni.tickets.rs/program/hotel-88-1209")
//                        .prices(Collections.singletonList(800))
//                        .build(),
//                EventScraped
//                        .builder()
//                        .eventName("GETSIMANSKI VRT")
//                        .eventType("THEATER")
//                        .placeId("Atelje 212, scena \"Mira Trailović\" Svetogorska 21, 11000 Beograd")
//                        .picture("https://pb2eu.interticket.com/imgs/system-20/program/000/000/090/moja-ti-474-279-5695.jpg")
//                        .eventDateTime(LocalDateTime.of(LocalDate.of(2019, Month.SEPTEMBER, 15), LocalTime.of(20,0)))
//                        .source("https://nasceni.tickets.rs/program/getsimanski-vrt-914")
//                        .prices(Collections.singletonList(800))
//                        .build()
//        );
    }

}
