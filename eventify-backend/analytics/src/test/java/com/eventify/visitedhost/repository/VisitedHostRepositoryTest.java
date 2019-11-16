package com.eventify.visitedhost.repository;

import com.eventify.visitedhost.domain.VisitedHost;
import com.eventify.shared.demo.Sex;
import com.eventify.visitedhost.domain.VisitedHostBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@DataMongoTest
@AutoConfigureDataMongo
public class VisitedHostRepositoryTest {

    @Autowired
     VisitedHostRepository repository;

    @Before
    public  void setup(){
        repository.deleteAll();
    }

    @After
    public  void tearDown(){
        repository.deleteAll();
    }

    @Test
    public void whenAddVisitedHostShouldAddIt() {

        VisitedHost visitedHost1 =  new VisitedHostBuilder().userId(UUID.randomUUID()).hostId(UUID.randomUUID()).age(25).sex(Sex.MALE).build();
        repository.save(visitedHost1);
        VisitedHost visitedHost2 =  new VisitedHostBuilder().userId(UUID.randomUUID()).hostId(UUID.randomUUID()).age(24).sex(Sex.FEMALE).build();
        repository.save(visitedHost2);


        assert repository.findAll().size() == 2;
    }

}
