FROM openjdk
ADD zuul-server/target/zuul-server-1.0-SNAPSHOT.jar zuul.jar
EXPOSE 8762
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /zuul.jar" ]
