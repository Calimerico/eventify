package com.eventify.eventsonhost.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.eventsonhost.api.rest.EventHostConfirmed;
import com.eventify.eventsonhost.application.commands.AddUnconfirmedEventOnHost;
import com.eventify.eventsonhost.domain.EventsOnHost;
import com.eventify.eventsonhost.domain.EventsOnHostRepository;
import lombok.RequiredArgsConstructor;

import static com.eventify.shared.kafka.Topic.EVENTS_TOPIC;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class AddUnconfirmedEventOnHostHandler implements CommandHandler<AddUnconfirmedEventOnHost,Void> {

    private final EventsOnHostRepository eventsOnHostRepository;
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public Void handle(AddUnconfirmedEventOnHost addUnconfirmedEventOnHost) {
        EventsOnHost eventsOnHost = eventsOnHostRepository.findByHostId(addUnconfirmedEventOnHost.getHostId());
        if (eventsOnHost.isConfirmedByDefault()) {
            eventsOnHost.addConfirmedEvent(addUnconfirmedEventOnHost.getEventId());
            kafkaEventProducer.send(EventHostConfirmed
                    .builder()
                    .hostId(addUnconfirmedEventOnHost.getHostId())
                    .eventId(addUnconfirmedEventOnHost.getEventId())
                    .build(),
                    EVENTS_TOPIC
            );
        } else {
            eventsOnHost.addUnconfirmedEvent(addUnconfirmedEventOnHost.getEventId());
        }
        eventsOnHostRepository.save(eventsOnHost);
        return null;
    }
}
