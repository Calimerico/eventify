package com.example.demo;

/**
 * Created by spasoje on 30-Nov-18.
 */
public interface Gate {
    <Result> Result dispatch (Command<Result> command);
}
