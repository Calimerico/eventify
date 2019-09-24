package com.eventify.shared.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "includeKafka",havingValue = "true")
@RequiredArgsConstructor
public class MessageChannelFactory {
    private final KafkaStreams kafkaStreams;

    //todo test this!
    public MessageChannel create(Topic topic) {
        switch (topic) {
            case EVENTS_TOPIC:
                return kafkaStreams.eventsTopicOutputChannel();
            case PLACES_TOPIC:
                return kafkaStreams.placesTopicOutputChannel();
            default:
                throw new IllegalArgumentException("Topic " + topic.getName() + " does not exist!");
        }
    }
}
