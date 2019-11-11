package com.eventify.visitedevent.application.commands;

import com.eventify.visitedevent.domain.VisitedEvent;
import com.eventify.shared.demo.Command;
import com.eventify.visitedevent.domain.VisitedEventBuilder;
import lombok.Builder;
import lombok.Value;
import java.util.UUID;

/**
 * Created by spasoje on 15-Dec-18.
 */
@Value
@Builder
public class CreateVisitedEvent implements Command<VisitedEvent> {
    private UUID userId;
    private Integer age;


    public VisitedEvent buildDomain(){
        return  new VisitedEventBuilder().userId(userId).age(age).build();
    }
}
