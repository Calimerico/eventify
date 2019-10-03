package com.eventify.eventsonhost.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.UUID;

public class EventsOnHostDomainTest {

    @Test
    public void name() {
        EventsOnHost eventsOnHost = new EventsOnHost("pera");
        UUID unconfirmedEvent = UUID.randomUUID();
        UUID confirmedEvent = UUID.randomUUID();
        eventsOnHost.addUnconfirmedEvent(unconfirmedEvent);
        eventsOnHost.addConfirmedEvent(confirmedEvent);
        Assertions.assertThat(eventsOnHost.getUnconfirmedEvents().size()).isEqualTo(1);
        Assertions.assertThat(eventsOnHost.getConfirmedEvents().size()).isEqualTo(1);
    }
}
