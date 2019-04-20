FROM openjdk
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ADD event/target/events-0.0.1-SNAPSHOT.jar events.jar
EXPOSE 8200
CMD ["/wait-for-it.sh", "postgres:5432", "kafka:9092", "--", "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /events.jar"]
