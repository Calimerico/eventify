package com.eventify.userban.infrastructure;

import com.eventify.userban.domain.UserBanInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by spasoje on 13-Dec-18.
 */
public interface UserBanRepository extends CrudRepository<UserBanInfo,UUID> {

}
