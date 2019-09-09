package com.eventify.shared.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaStreams {
    String EVENTS_TOPIC = "eventsTopic";

    @Input(EVENTS_TOPIC)
    SubscribableChannel eventsTopicInputChannel();

    @Output(EVENTS_TOPIC)
    MessageChannel eventsTopicOutputChannel();
}
