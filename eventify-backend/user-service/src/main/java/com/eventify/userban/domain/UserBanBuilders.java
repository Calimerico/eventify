package com.eventify.userban.domain;

import com.eventify.user.domain.UserAccount;
import lombok.Builder;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by spasoje on 03-Feb-19.
 */

public class UserBanBuilders {

    @Builder(builderMethodName = "aUserBanInfo")
    private static UserBanInfo createUserBanInfo(UserAccount userAccount, BanInfo... banInfos) {

        return new UserBanInfo(
                userAccount,
                banInfos == null ? new HashSet<>() : new HashSet<>(Arrays.asList(banInfos)));
    }
}
