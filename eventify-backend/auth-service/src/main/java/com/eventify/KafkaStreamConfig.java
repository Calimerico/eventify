package com.eventify;

import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by spasoje on 25-Feb-19.
 */
//TODO Wrong name, wrong package
@EnableBinding(KafkaStreams.class)//TODO Maybe this annotation goes at the same place as SpringBootApp annotation?
public class KafkaStreamConfig {
}
