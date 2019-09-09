package com.eventify.unconfirmedeventsonhost.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.shared.kafka.Topic;
import com.eventify.unconfirmedeventsonhost.api.rest.EventHostConfirmed;
import com.eventify.unconfirmedeventsonhost.application.commands.UnconfirmedEventOnHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHostRepository;
import lombok.RequiredArgsConstructor;

import static com.eventify.shared.kafka.Topic.EVENTS_TOPIC;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class AddUnconfirmedEventOnHostHandler implements CommandHandler<UnconfirmedEventOnHost,Void> {

    private final UnconfirmedEventsOnHostRepository unconfirmedEventsOnHostRepository;
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public Void handle(UnconfirmedEventOnHost unconfirmedEventOnHost) {
        UnconfirmedEventsOnHost unconfirmedEventsOnHost = unconfirmedEventsOnHostRepository.findByUserId(unconfirmedEventOnHost.getHostId());
        if (unconfirmedEventsOnHost.isConfirmedByDefault()) {
            kafkaEventProducer.send(EventHostConfirmed
                    .builder()
                    .hostId(unconfirmedEventOnHost.getHostId())
                    .eventId(unconfirmedEventOnHost.getEventId())
                    .build(),
                    EVENTS_TOPIC
            );
        } else {
            unconfirmedEventsOnHost.getUnconfirmedEvents().add(unconfirmedEventOnHost.getEventId());
            unconfirmedEventsOnHostRepository.save(unconfirmedEventsOnHost);
        }

        return null;
    }
}
