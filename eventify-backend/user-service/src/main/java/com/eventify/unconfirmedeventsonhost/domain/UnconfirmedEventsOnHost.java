package com.eventify.unconfirmedeventsonhost.domain;

import com.eventify.shared.ddd.UUIDAggregate;
import com.eventify.user.domain.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UnconfirmedEventsOnHost extends UUIDAggregate {

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserAccount user;
    private boolean confirmedByDefault = true;
    @ElementCollection
    private Set<UUID> unconfirmedEvents;
}
