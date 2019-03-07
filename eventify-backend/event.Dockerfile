FROM openjdk:13-alpine
ADD event/target/events-0.0.1-SNAPSHOT.jar events.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /events.jar" ]
