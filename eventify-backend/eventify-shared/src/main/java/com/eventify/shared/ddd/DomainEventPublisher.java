package com.eventify.shared.ddd;

import com.eventify.shared.demo.DomainEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class DomainEventPublisher implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static void publish(DomainEvent domainEvent) {
        context.getBean(DomainEventPublisherBean.class).publish(domainEvent);
    }

}
