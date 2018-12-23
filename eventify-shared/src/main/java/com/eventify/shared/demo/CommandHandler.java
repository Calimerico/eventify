package com.eventify.shared.demo;

/**
 * Created by spasoje on 22-Nov-18.
 */
public interface CommandHandler<Cmd extends Command<Result>, Result> {
    Result handle(Cmd cmd);
}
