FROM wurstmeister/kafka:2.11-2.0.0
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
CMD ["/wait-for-it.sh", "zookeper:2181", "--", "start-kafka.sh"]