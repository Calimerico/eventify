package com.eventify.auth.infrastructure;

import com.eventify.auth.domain.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by spasoje on 13-Dec-18.
 */
@Repository
public interface UserRepository extends CrudRepository<UserAccount,UUID> {
    Optional<UserAccount> findByUsername(String username);
}
