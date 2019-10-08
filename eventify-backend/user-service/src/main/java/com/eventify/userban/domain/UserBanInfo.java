package com.eventify.userban.domain;

import com.eventify.shared.DomainEventPublisher;
import com.eventify.shared.ddd.UUIDAggregate;
import com.eventify.user.domain.UserAccount;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.UUID;

@Entity
public class UserBanInfo extends UUIDAggregate {

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserAccount userAccount;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<BanInfo> banInfos;

    @PersistenceConstructor
    private UserBanInfo(DomainEventPublisher domainEventPublisher) {
        super(domainEventPublisher);
    }

    UserBanInfo(UserAccount userAccount, Set<BanInfo> banInfos, DomainEventPublisher domainEventPublisher) {
        super(domainEventPublisher);
        this.setId(userAccount.getId());
        this.userAccount = userAccount;
        this.banInfos = banInfos;
    }

    public static UserBanInfoBuilder builder() {
        return new UserBanInfoBuilder();
    }

    public void banUser(UUID adminId, String reasonForBan, LocalDateTime bannedUntil) {
        banInfos.add(new BanInfo(adminId, reasonForBan, LocalDateTime.now(), bannedUntil));
    }

    public void unbanUser(UUID adminId) {
        BanInfo banInfo = banInfos.stream().max(Comparator.comparing(BanInfo::getFromDate))
                .orElseThrow(() -> new IllegalStateException("This user never had ban!"));
        banInfo.unbanUser(adminId);
    }

    public Set<BanInfo> getBanInfos() {//todo we can update collection with this getter. We need to create BanInfoDto also
        return banInfos;
    }
}
