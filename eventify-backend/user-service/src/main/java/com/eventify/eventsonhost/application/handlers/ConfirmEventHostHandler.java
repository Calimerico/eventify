package com.eventify.eventsonhost.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.eventsonhost.api.rest.EventHostConfirmed;
import com.eventify.eventsonhost.application.commands.ConfirmEventHost;
import com.eventify.eventsonhost.domain.EventsOnHost;
import com.eventify.eventsonhost.domain.EventsOnHostRepository;
import lombok.RequiredArgsConstructor;

import static com.eventify.shared.kafka.Topic.EVENTS_TOPIC;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class ConfirmEventHostHandler implements CommandHandler<ConfirmEventHost, Void> {

    private final EventsOnHostRepository eventsOnHostRepository;
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public Void handle(ConfirmEventHost confirmEventHost) {
        EventsOnHost eventsOnHost = eventsOnHostRepository.findByHostId(confirmEventHost.getHostId());
        eventsOnHost.removeEvent(confirmEventHost.getEventId());
        eventsOnHost.addConfirmedEvent(confirmEventHost.getEventId());
        eventsOnHostRepository.save(eventsOnHost);
        kafkaEventProducer.send(EventHostConfirmed
                .builder()
                .eventId(confirmEventHost.getEventId())
                .hostId(confirmEventHost.getHostId())
                .build(), EVENTS_TOPIC);
        return null;
    }
}
