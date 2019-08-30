package com.eventify.config.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by spasoje on 25-Feb-19.
 */
public interface KafkaStreams {
    String OUTPUT = "eventWebScraperOutput";

    @Output(OUTPUT)
    MessageChannel outputChannel();
}
