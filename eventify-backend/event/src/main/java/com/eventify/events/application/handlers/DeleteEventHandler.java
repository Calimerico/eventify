package com.eventify.events.application.handlers;

import com.eventify.events.EventDeletedEvent;
import com.eventify.events.application.commands.DeleteEvent;
import com.eventify.events.domain.Host;
import com.eventify.events.domain.HostOnEvent;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.events.infrastructure.KafkaEventProducer;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.NoSuchElementException;
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
        Set<UUID> hostIds = CollectionUtils
                .emptyIfNull(eventRepository.loadById(deleteEvent.getId())
                        .getHosts()
                )
                .stream()
                .filter(HostOnEvent::isConfirmed)
                .map(hostOnEvent -> hostOnEvent.getHost().getId())
                .collect(Collectors.toSet());
        eventRepository.deleteById(deleteEvent.getId());
        kafkaEventProducer.send(EventDeletedEvent
                .builder()
                .eventId(deleteEvent.getId())
                .hosts(hostIds)
                .build()
        );
        return null;//todo return null?
    }
}
