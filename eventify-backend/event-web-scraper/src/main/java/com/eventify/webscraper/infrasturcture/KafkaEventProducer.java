package com.eventify.webscraper.infrasturcture;

import com.eventify.config.kafka.KafkaStreams;
import com.eventify.shared.demo.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Component
@RequiredArgsConstructor
//TODO I will duplicate this one for now, should be in com.eventify.shared folder
public class KafkaEventProducer {
    private final KafkaStreams kafkaStreams;

    public void send(DomainEvent event) {

        MessageChannel messageChannel = kafkaStreams.outputChannel();
        messageChannel.send(MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
