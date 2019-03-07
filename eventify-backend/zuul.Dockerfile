FROM openjdk:13-alpine
ADD zuul-server/target/zuul-server-1.0-SNAPSHOT.jar zuul.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /zuul.jar" ]
