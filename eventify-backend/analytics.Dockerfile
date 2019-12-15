FROM openjdk:13-alpine
RUN apk update && apk add bash
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ADD analytics/target/analytics-0.0.1-SNAPSHOT.jar analytics.jar
EXPOSE 8182
CMD ["/wait-for-it.sh", "mongo:27017", "--", "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /analytics.jar"]