package com.eventify.events.application.handlers;

import com.eventify.events.EventDeletedEvent;
import com.eventify.events.application.commands.DeleteEvent;
import com.eventify.events.domain.Host;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.events.infrastructure.KafkaEventProducer;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by spasoje on 02-Feb-19.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class DeleteEventHandler implements CommandHandler<DeleteEvent,Void> {

    private final EventRepository eventRepository;
    private final KafkaEventProducer kafkaEventProducer;//TODO This should be part of domain class?

    @Override
    public Void handle(DeleteEvent deleteEvent) {
        Set<UUID> hostIds = eventRepository.findById(deleteEvent.getId()).orElseThrow(RuntimeException::new).getHosts()
                .stream()
                .map(Host::getId)
                .collect(Collectors.toSet());//todo handle exception
        eventRepository.deleteById(deleteEvent.getId());
        kafkaEventProducer.send(EventDeletedEvent
                .builder()
                .eventId(deleteEvent.getId())
                .hosts(hostIds)
                .build()
        );
        return null;
    }
}
