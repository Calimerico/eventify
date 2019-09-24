package com.eventify.analytics;

import com.eventify.analytics.visitedpage.domain.VisitedPage;
import com.eventify.analytics.visitedpage.repository.VisitedPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.UUID;

@SpringBootApplication/*(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})*/
public class AnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsApplication.class, args);
	}

}
