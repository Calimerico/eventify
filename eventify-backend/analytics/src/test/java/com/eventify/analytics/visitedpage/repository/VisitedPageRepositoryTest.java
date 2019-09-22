package com.eventify.analytics.visitedpage.repository;

import com.eventify.analytics.visitedpage.domain.VisitedPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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

        VisitedPage visitedPage1 =  VisitedPage.builder().userId(UUID.randomUUID()).pageId(UUID.randomUUID()).build();
        repository.save(visitedPage1);
        VisitedPage visitedPage2 =  VisitedPage.builder().userId(UUID.randomUUID()).pageId(UUID.randomUUID()).build();
        repository.save(visitedPage2);

        assert repository.findAll().size() == 2;
    }

}
