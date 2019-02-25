package com.eventify;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by spasoje on 25-Feb-19.
 */
//TODO Name of class and package are wrong!
public interface KafkaStreams {
    String OUTPUT = "eventWebScraperOutput";

    @Output(OUTPUT)
    MessageChannel outputChannel();//TODO Maybe this have bad name also?
}
