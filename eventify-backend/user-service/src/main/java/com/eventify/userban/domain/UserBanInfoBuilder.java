package com.eventify.userban.domain;

import com.eventify.shared.DomainEventPublisher;
import com.eventify.user.domain.UserAccount;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by spasoje on 03-Feb-19.
 */

@Component
public class UserBanInfoBuilder {
    @Autowired
    private DomainEventPublisher domainEventPublisher;
    private UserAccount userAccount;
    private Set<BanInfo> banInfos;

    UserBanInfoBuilder() {
    }

    public UserBanInfoBuilder userAccount(UserAccount userAccount) {//todo here I should pass id and then here from repo get userAccount if needed. Remember that we (maybe?) want to reference aggregate only with id, not with reference
        this.userAccount = userAccount;
        return this;
    }

    public UserBanInfoBuilder banInfos(Set<BanInfo> banInfos) {
        this.banInfos = banInfos;
        return this;
    }

    public UserBanInfo build() {
        return new UserBanInfo(userAccount, banInfos, domainEventPublisher);
    }
}
