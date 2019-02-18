package com.eventify.events;

import com.eventify.events.domain.Place;
import com.eventify.events.domain.TheaterEvent;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.events.infrastructure.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by spasoje on 15-Dec-18.
 */
@Component
@RequiredArgsConstructor
//TODO If this doesn't work check is kafka is running because kafka is blocking...kafka should be optional not mandatory?
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final EventRepository eventRepository;//TODO Seeding should be done through SQL scripts.
    private final PlaceRepository placeRepository;

    @Override
    public void run(String... strings) throws Exception {

        Place place = getPlace();
        placeRepository.save(place);
        List<TheaterEvent> events = new ArrayList<>();
        TheaterEvent theaterEvent = new TheaterEvent("comedy");
        theaterEvent.setEventName("Gospodja ministarka");
        theaterEvent.setPlace(place);
        theaterEvent.setEventDateTime(LocalDateTime.now());
        events.add(theaterEvent);
        TheaterEvent theaterEvent2 = new TheaterEvent("comedy");
        theaterEvent2.setEventName("Gospodja ministarka2");
        theaterEvent2.setPlace(place);
        theaterEvent2.setEventDateTime(LocalDateTime.of(1991,2,2,2,2));
        events.add(theaterEvent2);
        eventRepository.saveAll(events);
    }

    public Place getPlace() {
        List<String> placeNames = new ArrayList<>();
        placeNames.add("Narodno pozoriste");
        return new Place(placeNames);
    }
}
