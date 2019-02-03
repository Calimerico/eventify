package com.eventify.auth.infrastructure;

import com.eventify.auth.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by spasoje on 13-Dec-18.
 */
@Repository
public interface UserRepository extends CrudRepository<User,UUID> {
    Optional<User> findByUsername(String username);
}
