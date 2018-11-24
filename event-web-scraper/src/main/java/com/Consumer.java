package com;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by spasoje on 23-Nov-18.
 */
@Service
public class Consumer {
    @KafkaListener(topics = "cqrs")
    public void receiveTopic1(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("Receiver on topic1: key is " + consumerRecord.key() + " value is " + consumerRecord.value());
    }
}
