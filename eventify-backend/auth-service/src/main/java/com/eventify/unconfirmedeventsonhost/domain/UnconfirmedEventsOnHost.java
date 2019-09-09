package com.eventify.unconfirmedeventsonhost.domain;

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
public class UnconfirmedEventsOnHost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserAccount user;
    private boolean confirmedByDefault = true;
    @ElementCollection
    private Set<UUID> unconfirmedEvents;
}
