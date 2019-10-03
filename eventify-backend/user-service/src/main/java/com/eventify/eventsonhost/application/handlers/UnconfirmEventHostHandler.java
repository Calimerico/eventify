package com.eventify.eventsonhost.application.handlers;

import com.eventify.eventsonhost.api.rest.EventHostUnconfirmed;
import com.eventify.eventsonhost.application.commands.UnconfirmEventHost;
import com.eventify.eventsonhost.domain.EventsOnHost;
import com.eventify.eventsonhost.domain.EventsOnHostRepository;
import com.eventify.shared.demo.CommandHandler;
import com.eventify.shared.kafka.KafkaEventProducer;
import lombok.RequiredArgsConstructor;

import static com.eventify.shared.kafka.Topic.EVENTS_TOPIC;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class UnconfirmEventHostHandler implements CommandHandler<UnconfirmEventHost, Void> {

    private final EventsOnHostRepository eventsOnHostRepository;
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public Void handle(UnconfirmEventHost unconfirmEventHost) {
        EventsOnHost eventsOnHost = eventsOnHostRepository.findByHostId(unconfirmEventHost.getHostId());
        eventsOnHost.removeEvent(unconfirmEventHost.getEventId());
        eventsOnHost.addUnconfirmedEvent(unconfirmEventHost.getEventId());
        eventsOnHostRepository.save(eventsOnHost);
        kafkaEventProducer.send(EventHostUnconfirmed
                .builder()
                .eventId(unconfirmEventHost.getEventId())
                .hostId(unconfirmEventHost.getHostId())
                .build(), EVENTS_TOPIC);
        return null;
    }
}
