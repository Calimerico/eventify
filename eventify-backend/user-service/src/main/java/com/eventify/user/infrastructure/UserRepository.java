package com.eventify.user.infrastructure;

import com.eventify.user.domain.UserAccount;
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

    default UserAccount loadUser(UUID id) {
        return findById(id).orElseThrow(() -> new IllegalStateException("User with id " + id + "does not exist"));
    }
}
