package com.eventify.shared.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaStreams {
    String EVENTS_TOPIC_INPUT_CHANNEL = "eventsTopicInputChannel";
    String EVENTS_TOPIC_OUTPUT_CHANNEL = "eventsTopicOutputChannel";

    @Input(EVENTS_TOPIC_INPUT_CHANNEL)
    SubscribableChannel eventsTopicInputChannel();

    @Output(EVENTS_TOPIC_OUTPUT_CHANNEL)
    MessageChannel eventsTopicOutputChannel();
}
