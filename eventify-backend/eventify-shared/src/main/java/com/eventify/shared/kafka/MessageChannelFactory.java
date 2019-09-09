package com.eventify.shared.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageChannelFactory {
    private final KafkaStreams kafkaStreams;

    public MessageChannel create(Topic topic) {
        switch (topic) {
            case EVENTS_TOPIC:
                return kafkaStreams.eventsTopicOutputChannel();
            default:
                throw new IllegalArgumentException("Topic " + topic.getName() + " does not exist!");
        }
    }
}
