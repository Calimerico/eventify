package com.eventify.event.application.handlers;

import com.eventify.event.application.commands.UpdateEvent;
import com.eventify.event.domain.Event;
import com.eventify.event.domain.EventBuilder;
import com.eventify.event.infrastructure.EventRepository;
import com.eventify.shared.config.auth.IntegrationTest;
import com.eventify.shared.demo.EventType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class UpdateEventHandlerTest extends IntegrationTest {

    @Autowired
    private UpdateEventHandler updateEventHandler;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventBuilder eventBuilder;

    private Event event;

    @Before
    public void setUp() throws Exception {
        event = eventBuilder
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(1))
                .eventType(EventType.THEATER)
                .build();
        eventRepository.save(event);
    }

    @Test
    public void updateEventTest() {
        //given
        //when Handle update
        String newName = "name2";
        updateEventHandler.handle(UpdateEvent
                .builder()
                .id(event.getId())
                .eventName(newName)
                .eventDateTime(LocalDateTime.now().plusDays(1))
                .eventType(EventType.THEATER)
                .build()
        );
        //then
        Optional<Event> afterUpdate = eventRepository.findById(event.getId());
        assertThat(afterUpdate.isPresent()).isTrue();
        assertThat(afterUpdate.get().getEventName()).isEqualTo(newName);
    }
}
