package com.eventify.visitedevent.repository;

import com.eventify.visitedevent.domain.VisitedEvent;
import com.eventify.shared.demo.EventType;
import com.eventify.shared.demo.Sex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureDataMongo
public class VisitedEventRepositoryTest {

    @Autowired
     VisitedEventRepository repository;

    @Before
    public  void setup(){
        repository.deleteAll();
    }

    @After
    public  void tearDown(){
        repository.deleteAll();
    }

    @Test
    public void whenAddVisitedPageShouldAddIt() {

        VisitedEvent visitedEvent1 =  VisitedEvent.builder()
                .userId(UUID.randomUUID())
                .eventId(UUID.randomUUID())
                .age(50).sex(Sex.FEMALE)
                .eventType(EventType.THEATER)
                .hostIds(Arrays.asList(UUID.randomUUID(),UUID.randomUUID()))
                .placeId(UUID.randomUUID())
                .build();
        repository.save(visitedEvent1);

        VisitedEvent visitedEvent2 =  VisitedEvent.builder()
                .userId(UUID.randomUUID())
                .eventId(UUID.randomUUID())
                .age(50).sex(Sex.FEMALE)
                .eventType(EventType.THEATER)
                .hostIds(Arrays.asList(UUID.randomUUID(),UUID.randomUUID()))
                .placeId(UUID.randomUUID())
                .build();
        repository.save(visitedEvent2);

        assert repository.findAll().size() == 2;
    }

}
