package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringGatewayApp {

	@Value("${userService.url}")
	private String userServiceUrl;
	@Value("${eventService.url}")
	private String eventServiceUrl;
	@Value("${webScraperService.url}")
	private String webScraperServiceUrl;
	@Value("${placeService.url}")
	private String placeServiceUrl;

	public static void main(String[] args) {
		SpringApplication.run(SpringGatewayApp.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/event/**")
						.filters(f -> f.rewritePath("/event/", "/"))
						.uri(eventServiceUrl)
						.id("event-service"))
				.route(p -> p
						.path("/user/**")
						.filters(f -> f.rewritePath("/user/", "/"))
						.uri(userServiceUrl)
						.id("user-service"))
				.route(p -> p
						.path("/scraper/**")
						.filters(f -> f.rewritePath("/scraper/", "/"))
						.uri(webScraperServiceUrl)
						.id("web-scraper"))
				.route(p -> p
						.path("/place/**")
						.filters(f -> f.rewritePath("/place/", "/"))
						.uri(placeServiceUrl)
						.id("place-service"))
				.build();
	}

}
