package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class SpringGatewayApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringGatewayApp.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/event/**")
						.filters(f -> f.rewritePath("/event/", "/"))
						.uri("lb://event-service/")
						.id("event-service"))
				.route(p -> p
						.path("/user/**")
						.filters(f -> f.rewritePath("/user/", "/"))
						.uri("lb://user-service/")
						.id("user-service"))
				.route(p -> p
						.path("/scraper/**")
						.filters(f -> f.rewritePath("/scraper/", "/"))
						.uri("lb://web-scraper/")
						.id("web-scraper"))
				.route(p -> p
						.path("/place/**")
						.filters(f -> f.rewritePath("/place/", "/"))
						.uri("lb://place-service/")
						.id("place-service"))
				.build();//todo here we have a problem when start locally event service UnknownHostException: event: name does not resolve
	}

}
