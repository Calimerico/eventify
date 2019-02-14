package com.eventify.events.infrastructure;

import com.eventify.events.domain.Host;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by spasoje on 04-Feb-19.
 */
public interface HostRepository extends CrudRepository<Host,UUID> {
}
