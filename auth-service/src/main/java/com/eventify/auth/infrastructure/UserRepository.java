package com.eventify.auth.infrastructure;

import com.eventify.auth.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by spasoje on 13-Dec-18.
 */
@Repository
public interface UserRepository extends CrudRepository<User,String> {
}
