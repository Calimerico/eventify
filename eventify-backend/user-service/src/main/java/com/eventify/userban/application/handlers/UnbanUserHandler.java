package com.eventify.userban.application.handlers;

import com.eventify.shared.net.CommandHandler;
import com.eventify.userban.application.commands.UnbanUser;
import com.eventify.userban.domain.UserBanInfo;
import com.eventify.userban.infrastructure.UserBanRepository;
import lombok.RequiredArgsConstructor;

/**
 * Created by spasoje on 13-Dec-18.
 */
@CommandHandler
@RequiredArgsConstructor
public class UnbanUserHandler implements com.eventify.shared.demo.CommandHandler<UnbanUser,Void> {

    private final UserBanRepository userBanRepository;

    @Override
    public Void handle(UnbanUser unbanUser) {
        //todo check adminId! Maybe user with that id is not admin!
        UserBanInfo userBanInfo = userBanRepository.findById(unbanUser.getUserId()).orElseThrow(RuntimeException::new);//todo
        userBanInfo.unbanUser(unbanUser.getAdminId());
        userBanRepository.save(userBanInfo);
        return null;
    }
}
