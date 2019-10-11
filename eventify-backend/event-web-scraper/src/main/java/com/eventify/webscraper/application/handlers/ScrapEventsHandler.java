package com.eventify.webscraper.application.handlers;

import com.eventify.shared.ddd.DomainEventPublisher;
import com.eventify.webscraper.application.commands.ScrapEvents;
import com.eventify.webscraper.domain.EventsWebScraper;
import com.eventify.shared.demo.CommandHandler;
import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * Created by spasoje on 30-Nov-18.
 */
@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class ScrapEventsHandler implements CommandHandler<ScrapEvents,Void> {

    private final List<EventsWebScraper> eventsWebScrapers;

    @Override
    public Void handle(ScrapEvents scrapEvents) {
        eventsWebScrapers.forEach(eventsWebScraper -> DomainEventPublisher.publish(eventsWebScraper.scrapEvents()));
        return null;
    }
}
