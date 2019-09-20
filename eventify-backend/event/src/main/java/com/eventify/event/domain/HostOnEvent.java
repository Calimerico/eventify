package com.eventify.event.domain;

import com.eventify.shared.ddd.UUIDEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class HostOnEvent extends UUIDEntity {

    @OneToOne
    private Host host;
    private boolean confirmed;

}
