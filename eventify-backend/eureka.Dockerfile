FROM openjdk:13-alpine
ADD eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar eureka.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /eureka.jar" ]
