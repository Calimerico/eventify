package com.eventifyshared.demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Component
public class StandardGate implements Gate {

    @Autowired
    private BeanFactory beanFactory;

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
