package com.eventify.unconfirmedeventsonhost.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.shared.kafka.Topic;
import com.eventify.unconfirmedeventsonhost.api.rest.EventHostConfirmed;
import com.eventify.unconfirmedeventsonhost.application.commands.ConfirmEventHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHostRepository;
import lombok.RequiredArgsConstructor;

import static com.eventify.shared.kafka.Topic.EVENTS_TOPIC;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class ConfirmEventHostHandler implements CommandHandler<ConfirmEventHost, Void> {

    private final UnconfirmedEventsOnHostRepository unconfirmedEventsOnHostRepository;
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public Void handle(ConfirmEventHost confirmEventHost) {
        UnconfirmedEventsOnHost byUserId = unconfirmedEventsOnHostRepository.findByUserId(confirmEventHost.getHostId());
        byUserId.getUnconfirmedEvents().remove(confirmEventHost.getEventId());
        unconfirmedEventsOnHostRepository.save(byUserId);
        kafkaEventProducer.send(EventHostConfirmed
                .builder()
                .eventId(confirmEventHost.getEventId())
                .hostId(confirmEventHost.getHostId())
                .build(), EVENTS_TOPIC);
        return null;
    }
}
