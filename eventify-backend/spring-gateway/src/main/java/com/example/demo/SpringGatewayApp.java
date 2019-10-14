package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

@SpringBootApplication
@EnableEurekaClient
public class SpringGatewayApp {

	@Value("${eventServiceUrl}")
	private String eventServiceUrl;

	@Value("${userServiceUrl}")
	private String userServiceUrl;

	@Value("${webScraperServiceUrl}")
	private String webScraperServiceUrl;

	@Value("${placeServiceUrl}")
	private String placeServiceUrl;

	public static void main(String[] args) {
		SpringApplication.run(SpringGatewayApp.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/event/**")
						.filters(f -> f.rewritePath("/event/", "/")
								.addResponseHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION))
						.uri(eventServiceUrl)
						.id("event-service"))
				.route(p -> p
						.path("/user/**")
						.filters(f -> f.rewritePath("/user/", "/")
								.addResponseHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION))
						.uri(userServiceUrl)
						.id("user-service"))
				.route(p -> p
						.path("/scraper/**")
						.filters(f -> f.rewritePath("/scraper/", "/")
								.addResponseHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION))
						.uri(webScraperServiceUrl)
						.id("web-scraper"))
				.route(p -> p
						.path("/place/**")
						.filters(f -> f.rewritePath("/place/", "/")
								.addResponseHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION))
						.uri(placeServiceUrl)
						.id("place-service"))
				.build();//todo here we have a problem when start locally event service UnknownHostException: event: name does not resolve
	}

}
