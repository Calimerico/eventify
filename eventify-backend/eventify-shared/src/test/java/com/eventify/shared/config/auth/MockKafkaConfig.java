package com.eventify.shared.config.auth;

import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.shared.kafka.KafkaStreams;
import com.eventify.shared.kafka.MessageChannelFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.SubscribableChannel;

@TestConfiguration
public class MockKafkaConfig {

    @MockBean
    private KafkaEventProducer kafkaEventProducer;

    @MockBean
    private MessageChannelFactory messageChannelFactory;//todo think of better way of mocking kafka than this 5 MockBeans

    @MockBean
    private KafkaStreams kafkaStreams;

    @MockBean(name = "eventsTopicInputChannel")
    private SubscribableChannel eventsTopicInputChannel;

    @MockBean(name = "placesTopicInputChannel")
    private SubscribableChannel placesTopicInputChannel;
}
