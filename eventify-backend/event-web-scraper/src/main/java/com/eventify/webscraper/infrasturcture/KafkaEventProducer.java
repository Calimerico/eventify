package com.eventify.webscraper.infrasturcture;

import com.eventify.KafkaStreams;
import com.eventify.shared.demo.DomainEvent;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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
