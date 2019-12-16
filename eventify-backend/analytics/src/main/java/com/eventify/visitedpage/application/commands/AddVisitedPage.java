package com.eventify.visitedpage.application.commands;

import com.eventify.shared.demo.Command;
import com.eventify.visitedpage.domain.VisitedPage;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AddVisitedPage implements Command<VisitedPage> {
    private Long pageId;
    private UUID userId;
}
