package com.eventifyshared.net;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * Created by spasoje on 30-Nov-18.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
@Transactional
@Inherited
@Documented
public @interface CommandHandler {
}
