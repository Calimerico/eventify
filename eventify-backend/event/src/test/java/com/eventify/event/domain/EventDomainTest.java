package com.eventify.event.domain;

import com.eventify.event.domain.Event;
import com.eventify.event.infrastructure.EventRepository;
import com.eventify.place.domain.Place;
import com.eventify.place.infrastructure.PlaceRepository;
import com.eventify.shared.demo.EventType;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.*;

public class EventDomainTest {

    @Test
    public void throwExceptionIfNameIsNull() {
        assertThatThrownBy(() -> Event
                .builder()
                .eventType(EventType.THEATER)
                .eventDateTime(LocalDateTime.now())
                .description("desc")
                .place(new Place(UUID.randomUUID(),"place name","SD",1l,2l))
                .build()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void throwExceptionIfTypeIsNull() {
        assertThatThrownBy(() -> Event
                .builder()
                .eventName("name")
                .eventDateTime(LocalDateTime.now())
                .description("desc")
                .place(new Place(UUID.randomUUID(),"place name","SD",1l,2l))
                .build()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void throwExceptionIfEventDateTimeIsNull() {
        assertThatThrownBy(() -> Event
                .builder()
                .eventName("name")
                .eventType(EventType.THEATER)
                .description("desc")
                .place(new Place(UUID.randomUUID(),"place name","SD",1l,2l))
                .build()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void throwExceptionIfEventDateTimeIsInPast() {
        assertThatThrownBy(() -> Event
                .builder()
                .eventName("name")
                .eventDateTime(LocalDateTime.now().minusDays(2))
                .eventType(EventType.THEATER)
                .description("desc")
                .place(new Place(UUID.randomUUID(),"place name","SD",1l,2l))
                .build()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void noExceptionsWhenAllFieldsAreFine() {
        assertThatCode(() -> Event
                .builder()
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(2L))
                .eventType(EventType.THEATER)
                .host(new Host(singletonList("some host")))
                .description("desc")
                .place(new Place(UUID.randomUUID(), "place name", "SD", 1l, 2l))
                .build()).doesNotThrowAnyException();
    }

    @Test
    public void throwExceptionIfEventDoesNotContainHostForConfirmation() {
        Event event = Event
                .builder()
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(2L))
                .eventType(EventType.THEATER)
                .host(new Host(singletonList("some host")))
                .description("desc")
                .place(new Place(UUID.randomUUID(), "place name", "SD", 1l, 2l))
                .build();
        assertThatThrownBy(() -> {
            event.confirmHost(UUID.randomUUID());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void noExceptionsWhenWeTryToUpdateHostAndHostIsPresent() {
        Host host = new Host(singletonList("some host"));
        Event event = Event
                .builder()
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(2L))
                .eventType(EventType.THEATER)
                .host(host)
                .description("desc")
                .place(new Place(UUID.randomUUID(), "place name", "SD", 1l, 2l))
                .build();
        assertThat(event.findConfirmedHostIds()).isEmpty();
        assertThat(event.findUnconfirmedHostIds()).contains(host.getId());
        assertThatCode(() -> event.confirmHost(host.getId())).doesNotThrowAnyException();
        assertThat(event.findConfirmedHostIds()).contains(host.getId());
        assertThat(event.findUnconfirmedHostIds()).isEmpty();
    }

    @Test
    public void userIsHostOfEventIfConfirmationHappened() {
        Host host = new Host(singletonList("some host"));
        Event event = Event
                .builder()
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(2L))
                .eventType(EventType.THEATER)
                .host(host)
                .description("desc")
                .place(new Place(UUID.randomUUID(), "place name", "SD", 1l, 2l))
                .build();
        event.confirmHost(host.getId());
        assertThat(event.isUserHostForThisEvent(host.getId())).isTrue();
    }

    @Test
    public void userIsNotHostOfEventIfConfirmationHasntHappened() {
        Host host = new Host(singletonList("some host"));
        Event event = Event
                .builder()
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(2L))
                .eventType(EventType.THEATER)
                .host(host)
                .description("desc")
                .place(new Place(UUID.randomUUID(), "place name", "SD", 1l, 2l))
                .build();
        assertThat(event.isUserHostForThisEvent(host.getId())).isFalse();
    }
}
