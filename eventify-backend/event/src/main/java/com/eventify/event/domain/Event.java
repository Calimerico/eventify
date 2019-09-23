package com.eventify.event.domain;

/**
 * Created by spasoje on 01-Nov-18.
 */

import com.eventify.shared.ddd.UUIDAggregate;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

/**
 * Created by spasoje on 15-Jun-17.
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Event extends UUIDAggregate {
    private String eventName;

    @ManyToMany
    private Set<HostOnEvent> hosts;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @OneToOne
    private Place place;
    private LocalDateTime eventDateTime;
    private String description;
    private String source;
    private String profilePicture;
    @ElementCollection
    private List<Integer> prices;//TODO Introduce Ticket entity or maybe embeddable?

    public void confirmHost(UUID hostId) {
        getHosts()
                .stream()
                .filter(hostOnEvent -> hostOnEvent.getHost().getId().equals(hostId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Event " + getId() + " does not contain host " + hostId))
                .setConfirmed(true);
    }

    public Set<UUID> findConfirmedHosts() {
        return emptyIfNull(hosts).stream()
                .filter(hostOnEvent -> Objects.nonNull(hostOnEvent) && hostOnEvent.isConfirmed())
                .map(hostOnEvent -> hostOnEvent.getHost().getId())
                .collect(toSet());
    }

    public Set<UUID> findUnconfirmedHosts() {
        return emptyIfNull(hosts).stream()
                .filter(hostOnEvent -> Objects.nonNull(hostOnEvent) && !hostOnEvent.isConfirmed())
                .map(hostOnEvent -> hostOnEvent.getHost().getId())
                .collect(toSet());
    }

    public boolean isUserHostForThisEvent(UUID userId) {
        return findConfirmedHosts().contains(userId);
    }

    public UUID getPlaceId() {
        return getPlace() == null ? null : getPlace().getId();
    }
}

