FROM openjdk:13-alpine
ADD spring-admin/target/spring-admin-0.0.1-SNAPSHOT.jar spring-admin.jar
EXPOSE 5060
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /spring-admin.jar" ]
