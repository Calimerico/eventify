#!/bin/bash
cd eventify-shared
mvn clean install 

cd ../event
mvn clean install -DskipTests=true

cd ../analytics
mvn clean install -DskipTests=true

cd ../data-seeder
mvn clean install -DskipTests=true

cd ../eureka-server
mvn clean install -DskipTests=true

cd ../event-web-scraper
mvn clean install -DskipTests=true

cd ../place-service
mvn clean install -DskipTests=true

cd ../spring-admin
mvn clean install -DskipTests=true

cd ../spring-gateway
mvn clean install -DskipTests=true

cd ../user-service
mvn clean install -DskipTests=true

docker-compose up -d
