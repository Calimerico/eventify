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

    @ElementCollection
    private List<String> names;

    public Host(List<String> names) {
        this.names = names;
        checkAggregate();
    }

    private Host() {
    }

    private void checkAggregate() {
        if (names == null || names.isEmpty()) {
            throw new IllegalStateException("Host must has a name!");
        }
    }
    public static HostBuilder builder() {
        return new HostBuilder();
    }

    public List<String> getNames() {
        return names;
    }

    public static class HostBuilder {
        private List<String> names;

        HostBuilder() {
        }

        public Host.HostBuilder names(List<String> names) {
            this.names = names;
            return this;
        }

        public Host build() {
            return new Host(names);
        }
    }
}
