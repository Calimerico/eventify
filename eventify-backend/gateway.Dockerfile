FROM openjdk:13-alpine
ADD spring-gateway/target/spring-gateway-0.0.1-SNAPSHOT.jar gateway.jar
EXPOSE 8762
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /gateway.jar" ]
