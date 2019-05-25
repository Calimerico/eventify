package com.eventify.userban.domain;

import com.eventify.auth.domain.UserAccount;
import com.eventify.shared.demo.Sex;
import lombok.Builder;
import lombok.Singular;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by spasoje on 03-Feb-19.
 */

public class UserBanBuilders {

    @Builder(builderMethodName = "aUserBanInfo")
    private static UserBanInfo createUserBanInfo(UserAccount userAccount, BanInfo... banInfos) {

        return new UserBanInfo(
                userAccount,
                banInfos == null ? new HashSet<>() : new HashSet<>(Arrays.asList(banInfos)));//todo
    }
}
