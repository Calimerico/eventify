FROM openjdk:13-alpine
RUN apk update && apk add bash
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ADD event-web-scraper/target/event-web-scraper-1.0-SNAPSHOT.jar event-web-scraper.jar
EXPOSE 8222
CMD ["/wait-for-it.sh", "postgres:5432", "kafka:9092", "--", "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /event-web-scraper.jar"]
