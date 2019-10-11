package com.eventify.eventsonhost.domain;

import com.eventify.shared.ddd.UUIDAggregate;
import com.eventify.user.domain.UserAccount;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
class Host extends UUIDAggregate {

    @OneToOne
    private UserAccount userAccount;
    private String name;

    Host(UserAccount userAccount) {
        this.userAccount = userAccount;
        setId(userAccount.getId());
    }

    private Host() {
    }

    Host(String name) {
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
