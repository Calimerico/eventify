package com.eventify.userban.domain;

import com.eventify.shared.ddd.UUIDEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class BanInfo extends UUIDEntity {

    private UUID adminWhichBannedUser;
    private UUID adminWhichUnbannedUser;
    private String reasonForBan;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    BanInfo(UUID adminIn, String reasonForBan, LocalDateTime fromDate, LocalDateTime toDate) {
        this.adminWhichBannedUser = adminIn;
        this.reasonForBan = reasonForBan;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
