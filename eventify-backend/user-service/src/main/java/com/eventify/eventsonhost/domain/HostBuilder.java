package com.eventify.eventsonhost.domain;

import com.eventify.user.domain.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class HostBuilder {

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
        return new Host(userAccount);
    }
}
