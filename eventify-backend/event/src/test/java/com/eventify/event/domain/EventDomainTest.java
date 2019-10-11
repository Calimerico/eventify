package com.eventify.event.domain;

import com.eventify.place.domain.Place;
import com.eventify.place.domain.PlaceBuilder;
import com.eventify.shared.demo.EventType;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class EventDomainTest {

    private EventBuilder eventBuilder = new EventBuilder();//todo those 2 builders have publisher null
    private PlaceBuilder placeBuilder = new PlaceBuilder();


    @Test
    public void throwExceptionIfNameIsNull() {
        assertThatThrownBy(() -> eventBuilder
                .eventType(EventType.THEATER)
                .eventDateTime(LocalDateTime.now().plusDays(1))
                .description("desc")
                .place(getPlace())
                .build()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void throwExceptionIfTypeIsNull() {
        assertThatThrownBy(() -> eventBuilder
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(1))
                .description("desc")
                .place(getPlace())
                .build()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void throwExceptionIfEventDateTimeIsNull() {
        assertThatThrownBy(() -> eventBuilder
                .eventName("name")
                .eventType(EventType.THEATER)
                .description("desc")
                .place(getPlace())
                .build()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void throwExceptionIfEventDateTimeIsInPast() {
        assertThatThrownBy(() -> eventBuilder
                .eventName("name")
                .eventDateTime(LocalDateTime.now().minusDays(2))
                .eventType(EventType.THEATER)
                .description("desc")
                .place(getPlace())
                .build()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void noExceptionsWhenAllFieldsAreFine() {
        assertThatCode(() -> eventBuilder
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(2L))
                .eventType(EventType.THEATER)
                .host(new Host("some host"))
                .description("desc")
                .place(getPlace())
                .build()).doesNotThrowAnyException();
    }

    @Test
    public void throwExceptionIfEventDoesNotContainHostForConfirmation() {
        Event event = eventBuilder
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(2L))
                .eventType(EventType.THEATER)
                .host(new Host("some host"))
                .description("desc")
                .place(getPlace())
                .build();
        assertThatThrownBy(() -> {
            event.confirmHost(UUID.randomUUID());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void noExceptionsWhenWeTryToUpdateHostAndHostIsPresent() {
        Host host = new Host("some host");
        Event event = eventBuilder
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(2L))
                .eventType(EventType.THEATER)
                .host(host)
                .description("desc")
                .place(getPlace())
                .build();
        assertThat(event.findConfirmedHostIds()).isEmpty();
        assertThat(event.findUnconfirmedHostIds()).contains(host.getId());
        assertThatCode(() -> event.confirmHost(host.getId())).doesNotThrowAnyException();
        assertThat(event.findConfirmedHostIds()).contains(host.getId());
        assertThat(event.findUnconfirmedHostIds()).isEmpty();
    }

    @Test
    public void userIsHostOfEventIfConfirmationHappened() {
        Host host = new Host("some host");
        Event event = eventBuilder
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(2L))
                .eventType(EventType.THEATER)
                .host(host)
                .description("desc")
                .place(getPlace())
                .build();
        event.confirmHost(host.getId());
        assertThat(event.isUserHostForThisEvent(host.getId())).isTrue();
    }

    @Test
    public void userIsNotHostOfEventIfConfirmationHasntHappened() {
        Host host = new Host("some host");
        Event event = eventBuilder
                .eventName("name")
                .eventDateTime(LocalDateTime.now().plusDays(2L))
                .eventType(EventType.THEATER)
                .host(host)
                .description("desc")
                .place(getPlace())
                .build();
        assertThat(event.isUserHostForThisEvent(host.getId())).isFalse();
    }

    public Place getPlace() {
        return placeBuilder
                .name("place name")
                .id(UUID.randomUUID())
                .longitude(1L)
                .latitude(2L)
                .city("SD")
                .build();
    }
}
