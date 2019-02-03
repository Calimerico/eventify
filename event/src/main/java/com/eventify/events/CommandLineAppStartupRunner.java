package com.eventify.events;

import com.eventify.events.domain.TheaterEvent;
import com.eventify.events.infrastructure.EventRepository;
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

    private final EventRepository eventRepository;//TODO Here I should add controller, not repo, but handlers are not implemented yet.

    @Override
    public void run(String... strings) throws Exception {
        List<TheaterEvent> events = new ArrayList<>();
        TheaterEvent theaterEvent = new TheaterEvent("comedy");
        theaterEvent.setEventName("Gospodja ministarka");
//        theaterEvent.setPlace("Narodno pozoriste");//TODO Add placeRepo?
        theaterEvent.setEventDateTime(LocalDateTime.now());
        events.add(theaterEvent);
        TheaterEvent theaterEvent2 = new TheaterEvent("comedy");
        theaterEvent2.setEventName("Gospodja ministarka2");
//        theaterEvent2.setPlaceId("Putujuce pozoriste");//TODO Add placeRepo?
        theaterEvent2.setEventDateTime(LocalDateTime.now());
        events.add(theaterEvent2);
        eventRepository.saveAll(events);
    }
}
