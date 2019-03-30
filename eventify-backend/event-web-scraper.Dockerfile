FROM openjdk:13-alpine
ADD event-web-scraper/target/event-web-scraper-1.0-SNAPSHOT.jar event-web-scraper.jar
EXPOSE 8222
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /event-web-scraper.jar" ]
