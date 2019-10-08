package com.eventify.eventsonhost.domain;

import com.eventify.shared.DomainEventPublisher;
import com.eventify.shared.ddd.UUIDAggregate;
import com.eventify.user.domain.UserAccount;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
class Host extends UUIDAggregate {

    @OneToOne
    private UserAccount userAccount;
    private String name;

    Host(UserAccount userAccount, DomainEventPublisher domainEventPublisher) {
        super(domainEventPublisher);
        this.userAccount = userAccount;
        setId(userAccount.getId());
    }

    @PersistenceConstructor
    private Host(DomainEventPublisher domainEventPublisher) {
        super(domainEventPublisher);
    }

    Host(String name, DomainEventPublisher domainEventPublisher) {
        super(domainEventPublisher);
        this.name = name;
    }

    public static HostBuilder builder() {
        return new com.eventify.eventsonhost.domain.HostBuilder();
    }

    public String getName() {
        if (userAccount != null) {
            return userAccount.getFirstName() + " " + userAccount.getLastName();
        }
        return name;
    }
}
