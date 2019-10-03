package com.eventify.event.domain;

import com.eventify.shared.ddd.UUIDEntity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by spasoje on 03-Feb-19.
 */
@Entity
class Host extends UUIDEntity {

    private String name;

    public Host(String name) {
        this.name = name;
        checkAggregate();
    }

    private Host() {
    }

    private void checkAggregate() {
        if (name == null || name.isEmpty()) {
            throw new IllegalStateException("Host must has a name!");
        }
    }
    public static HostBuilder builder() {
        return new HostBuilder();
    }

    public String getName() {
        return name;
    }

    public static class HostBuilder {
        private String name;

        HostBuilder() {
        }

        public Host.HostBuilder names(String name) {
            this.name = name;
            return this;
        }

        public Host build() {
            return new Host(name);
        }
    }
}
