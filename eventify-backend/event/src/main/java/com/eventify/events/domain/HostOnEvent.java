package com.eventify.events.domain;

import com.eventify.shared.ddd.UUIDEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class HostOnEvent extends UUIDEntity {

    @OneToOne
    private Host host;
    private boolean confirmed;

}
