package com;

/**
 * Created by spasoje on 21-Nov-18.
 */
public interface Gate <Result> {
    Result dispatch (Command<Result> command);
}
