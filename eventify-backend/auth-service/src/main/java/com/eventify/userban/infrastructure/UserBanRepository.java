package com.eventify.userban.infrastructure;

import com.eventify.userban.domain.UserBanInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by spasoje on 13-Dec-18.
 */
@Repository//todo remove this annotation? On some places CRUD repository works even without this one
public interface UserBanRepository extends CrudRepository<UserBanInfo,UUID> {

}
