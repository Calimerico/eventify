package com.eventify.events.application.handlers;

import com.eventify.events.EventDeletedEvent;
import com.eventify.events.application.commands.DeleteEvent;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.shared.demo.CommandHandler;
import com.eventify.shared.kafka.KafkaEventProducer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.eventify.shared.kafka.Topic.EVENTS_TOPIC;

/**
 * Created by spasoje on 02-Feb-19.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class DeleteEventHandler implements CommandHandler<DeleteEvent,Void> {

    private final EventRepository eventRepository;
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public Void handle(DeleteEvent deleteEvent) {
        Set<UUID> hostIds = eventRepository.loadById(deleteEvent.getId()).findConfirmedHosts();
        eventRepository.deleteById(deleteEvent.getId());
        kafkaEventProducer.send(EventDeletedEvent
                .builder()
                .eventId(deleteEvent.getId())
                .hosts(hostIds)
                .build(),
                EVENTS_TOPIC
        );
        return null;
    }
}
