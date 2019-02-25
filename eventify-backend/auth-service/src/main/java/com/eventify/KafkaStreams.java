package com.eventify;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by spasoje on 25-Feb-19.
 */
//TODO Name of class and package are wrong!
public interface KafkaStreams {
    String INPUT = "authInput";

    @Input(INPUT)
    SubscribableChannel inputChannel();//TODO Maybe this have bad name also?
}
