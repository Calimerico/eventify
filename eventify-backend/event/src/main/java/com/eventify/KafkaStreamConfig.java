package com.eventify;

import com.eventify.config.kafka.KafkaStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by spasoje on 25-Feb-19.
 */
//TODO Wrong name, wrong package
@EnableBinding(KafkaStreams.class)
public class KafkaStreamConfig {
}
