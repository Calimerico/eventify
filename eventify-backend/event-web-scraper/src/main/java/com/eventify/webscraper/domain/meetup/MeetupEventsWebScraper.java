package com.eventify.webscraper.domain.meetup;

import com.eventify.webscraper.domain.events.EventsScraped;
import com.eventify.webscraper.domain.EventsWebScraper;

/**
 * Created by spasoje on 21-Nov-18.
 */
//@Component TODO Temporary removed
public class MeetupEventsWebScraper implements EventsWebScraper {
    @Override
    public EventsScraped scrapEvents() {
//        List<Event> events = new ArrayList<>();
//        Document document;
//        try {
//            document = Jsoup.connect("https://www.meetup.com/find/events/tech/?allMeetups=false&radius=50&userFreeform=Belgrade%2C+Serbia&mcId=z1040618&mcName=Belgrade%2C+RS").get();
//            Elements links = document.select(".event-listing-container-li > ul > li .chunk > a");
//            System.out.println();
//            links.forEach(link -> {
//                Event event = new Event();
//                event.setSource(link.attr("href"));
//                try {
//                    Document eventDocument = Jsoup.connect(link.attr("href")).get();
//                    String datetimeString = eventDocument.select(".eventSideBar time").attr("datetime");
//
//                    long datetime = 0;
//                    if (!"".equals(datetimeString)){
//                        datetime = Long.parseLong(datetimeString);
//                    } else {
//                        System.out.println("Preskacemo fejl jebeni link " + link.attr("href"));
//                    }
//
//                    List<String> eventHostIds = new ArrayList<>();
//                    eventHostIds.add(eventDocument.select(".event-info-group--groupName").text());
//                    event.setPlaceId(eventDocument.select(".event-info-venueMap").attr("href"));
//                    event.setEventHostIds(eventHostIds);
//                    event.setEventName(eventDocument.select(".pageHead-headline").text());
//                    event.setDescription(eventDocument.select(".event-description p").text());
//                    event.setEventDateTime(ZonedDateTime.ofInstant(Instant.ofEpochMilli(datetime), ZoneId.of("Europe/Paris")));
//                    event.setEventType("Technology");//TODO We are scraping tech events now
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                events.add(event);
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
