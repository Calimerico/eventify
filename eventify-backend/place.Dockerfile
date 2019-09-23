FROM openjdk
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ADD place-service/target/place-service-1.0-SNAPSHOT.jar place.jar
EXPOSE 4500
CMD ["/wait-for-it.sh", "postgres:5432", "kafka:9092", "--", "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /place.jar"]
