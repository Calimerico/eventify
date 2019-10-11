package com.eventify.event.application.handlers;

import com.eventify.event.domain.events.EventDeletedEvent;
import com.eventify.event.application.commands.DeleteEvent;
import com.eventify.event.domain.EventRepository;
import com.eventify.shared.demo.CommandHandler;
import com.eventify.shared.kafka.KafkaEventProducer;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

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
        Set<UUID> hostIds = eventRepository.loadById(deleteEvent.getId()).findConfirmedHostIds();
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
