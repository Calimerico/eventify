package com.eventify.eventsonhost.domain;

import com.eventify.shared.DomainEventPublisher;
import com.eventify.user.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HostBuilder {

    @Autowired
    private DomainEventPublisher domainEventPublisher;
    private UserAccount userAccount;
    private String name;

    HostBuilder() {
    }

    public HostBuilder userAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public HostBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Host build() {
        return new Host(userAccount, domainEventPublisher);
    }
}
