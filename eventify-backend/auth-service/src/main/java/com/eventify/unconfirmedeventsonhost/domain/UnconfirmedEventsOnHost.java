package com.eventify.unconfirmedeventsonhost.domain;

import com.eventify.auth.domain.UserAccount;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UnconfirmedEventsOnHost {

    @Id
    private UserAccount user;
    private Set<UUID> unconfirmedEvents;
}
