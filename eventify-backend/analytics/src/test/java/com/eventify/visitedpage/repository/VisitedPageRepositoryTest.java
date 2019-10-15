package com.eventify.visitedpage.repository;

import com.eventify.visitedpage.domain.VisitedPage;
import com.eventify.visitedpage.domain.VisitedPageBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureDataMongo
public class VisitedPageRepositoryTest {

    @Autowired
     VisitedPageRepository repository;

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

        VisitedPage visitedPage1 =  new VisitedPageBuilder()
                .userId(UUID.randomUUID())
                .pageId(UUID.randomUUID())
                .build();
        repository.save(visitedPage1);

        VisitedPage visitedPage2 =  new VisitedPageBuilder()
                .userId(UUID.randomUUID())
                .pageId(UUID.randomUUID())
                .build();
        repository.save(visitedPage2);

        assert repository.findAll().size() == 2;
    }

}
