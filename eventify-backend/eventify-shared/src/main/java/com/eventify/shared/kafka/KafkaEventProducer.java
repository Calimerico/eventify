package com.eventify.shared.kafka;

import com.eventify.shared.demo.IntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

@Component
@ConditionalOnProperty(name = "includeKafka",havingValue = "true")
@RequiredArgsConstructor
public class KafkaEventProducer {
    private final MessageChannelFactory messageChannelFactory;

    public void send(IntegrationEvent event, Topic topic) {

        MessageChannel messageChannel = messageChannelFactory.create(topic);
        messageChannel.send(MessageBuilder
                .withPayload(event)
                .setHeader("eventType",event.getClass().getSimpleName())
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
