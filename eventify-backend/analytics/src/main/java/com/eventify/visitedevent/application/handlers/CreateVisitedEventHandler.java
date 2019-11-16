package com.eventify.visitedevent.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.visitedevent.application.commands.CreateVisitedEvent;
import com.eventify.visitedevent.domain.VisitedEvent;
import com.eventify.visitedevent.repository.VisitedEventRepository;
import lombok.RequiredArgsConstructor;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class CreateVisitedEventHandler implements CommandHandler<CreateVisitedEvent, VisitedEvent> {

    private final VisitedEventRepository visitedEventRepository;


    @Override
    public VisitedEvent handle(CreateVisitedEvent createVisitedEvent) {
        //TODO First check does event exist with event finder
        VisitedEvent event = visitedEventRepository.save(createVisitedEvent.buildDomain());
        return event;
    }

}
