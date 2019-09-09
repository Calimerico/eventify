package com.eventify.userban.domain;

import com.eventify.user.domain.UserAccount;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBanInfo {

    @Id
    private UUID id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserAccount userAccount;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<BanInfo> banInfos;

    public UserBanInfo(UserAccount userAccount, Set<BanInfo> banInfos) {
        this.id = userAccount.getId();
        this.userAccount = userAccount;
        this.banInfos = banInfos;
    }

    public void banUser(UUID adminId, String reasonForBan, LocalDateTime bannedUntil) {
        banInfos.add(new BanInfo(adminId, reasonForBan, LocalDateTime.now(), bannedUntil));
    }

    public void unbanUser(UUID adminId) {
        BanInfo banInfo = banInfos.stream().max(Comparator.comparing(BanInfo::getFromDate))
                .orElseThrow(() -> new IllegalStateException("This user never had ban!"));
        banInfo.setToDate(LocalDateTime.now());
        banInfo.setAdminWhichUnbannedUser(adminId);
    }
}
