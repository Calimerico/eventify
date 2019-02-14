package com.eventify.shared.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Component
@RequiredArgsConstructor
public class StandardGate implements Gate {

    private final BeanFactory beanFactory;

    public <Result> Result dispatch(Command<Result> command) {
//        beanFactory.getBean();
        //((DefaultListableBeanFactory) beanFactory).getBeansOfType(CommandHandler.class).values()
        //TODO hardcoded here command name + "Handler"
        String commandSimpleName = command.getClass().getSimpleName();
        return (Result) ((CommandHandler) beanFactory
                .getBean(commandSimpleName
                        .substring(0,1).toLowerCase()
                        + commandSimpleName.substring(1, commandSimpleName.length())
                        + "Handler")).handle(command);
    }
}
