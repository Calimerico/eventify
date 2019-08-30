package com.eventify.config.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by spasoje on 25-Feb-19.
 */
public interface KafkaStreams {
    String INPUT = "eventInput";
    String OUTPUT = "eventOutput";

    @Input(INPUT)
    SubscribableChannel inputChannel();

    @Output(OUTPUT)
    MessageChannel outputChannel();
}
