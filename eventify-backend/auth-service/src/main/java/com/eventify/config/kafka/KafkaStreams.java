package com.eventify.config.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by spasoje on 25-Feb-19.
 */
public interface KafkaStreams {
    String INPUT = "authInput";

    @Input(INPUT)
    SubscribableChannel inputChannel();
}
