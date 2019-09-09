FROM openjdk
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ADD data-seeder/target/data-seeder-1.0-SNAPSHOT.jar data-seeder.jar
EXPOSE 8200
CMD ["/wait-for-it.sh", "-t", "300", "event:8200", "--", "/wait-for-it.sh" ,"-t", "300", "user:9100",  "--", "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /data-seeder.jar dockerProfile"]
