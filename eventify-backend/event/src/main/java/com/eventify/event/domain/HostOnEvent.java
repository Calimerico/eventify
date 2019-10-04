package com.eventify.event.domain;

import com.eventify.shared.ddd.UUIDEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class HostOnEvent extends UUIDEntity {

    @OneToOne
    private Host host;
    private boolean confirmed;

    public HostOnEvent(Host host, boolean confirmed) {
        setId(host.getId());
        this.host = host;
        this.confirmed = confirmed;
    }

    public HostDto getHost() {
        return new HostDto(host.getId(),host.getName());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
