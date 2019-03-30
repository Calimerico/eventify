FROM openjdk:13-alpine
ADD auth-service/target/auth-service-1.0-SNAPSHOT.jar auth-service.jar
EXPOSE 9100
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /auth-service.jar" ]
