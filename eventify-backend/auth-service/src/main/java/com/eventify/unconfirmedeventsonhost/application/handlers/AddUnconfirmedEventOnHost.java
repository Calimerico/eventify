package com.eventify.unconfirmedeventsonhost.application.handlers;

import com.eventify.shared.demo.Command;
import com.eventify.shared.demo.CommandHandler;
import com.eventify.unconfirmedeventsonhost.application.commands.UnconfirmedEventOnHost;

@com.eventify.shared.net.CommandHandler
public class AddUnconfirmedEventOnHost implements CommandHandler<UnconfirmedEventOnHost,Void> {

    @Override
    public Void handle(UnconfirmedEventOnHost unconfirmedEventOnHost) {
        return null;
    }
}
