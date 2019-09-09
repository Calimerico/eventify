package com.eventify.userban.application.handlers;

import com.eventify.user.infrastructure.UserRepository;
import com.eventify.shared.net.CommandHandler;
import com.eventify.userban.application.commands.BanUser;
import com.eventify.userban.domain.UserBanBuilders;
import com.eventify.userban.domain.UserBanInfo;
import com.eventify.userban.infrastructure.UserBanRepository;
import lombok.RequiredArgsConstructor;

/**
 * Created by spasoje on 20-Dec-18.
 */
@CommandHandler
@RequiredArgsConstructor
public class BanUserHandler implements com.eventify.shared.demo.CommandHandler<BanUser, Void> {

    private final UserBanRepository userBanRepository;
    private final UserRepository userRepository;

    @Override
    public Void handle(BanUser banUser) {
        UserBanInfo userBanInfo = userBanRepository.findById(banUser.getUserId())
                .orElse(UserBanBuilders
                        .aUserBanInfo()
                        .userAccount(userRepository.loadUser(banUser.getUserId()))
                        .build()
                );
        userBanInfo.banUser(banUser.getAdminId(), banUser.getReasonForBan(), banUser.getBannedUntil());
        userBanRepository.save(userBanInfo);
        return null;
    }
}
