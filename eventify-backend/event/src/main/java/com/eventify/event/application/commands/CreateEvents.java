package com.eventify.event.application.commands;

import com.eventify.shared.demo.Command;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CreateEvents implements Command<Void> {
    private List<CreateEvent> events;
}
