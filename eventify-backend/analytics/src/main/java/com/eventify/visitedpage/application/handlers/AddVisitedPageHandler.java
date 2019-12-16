package com.eventify.visitedpage.application.handlers;

import com.eventify.shared.demo.CommandHandler;
import com.eventify.visitedpage.application.commands.AddVisitedPage;
import com.eventify.visitedpage.domain.VisitedPage;
import com.eventify.visitedpage.domain.VisitedPageBuilder;
import com.eventify.visitedpage.repository.VisitedPageRepository;
import lombok.RequiredArgsConstructor;

@com.eventify.shared.net.CommandHandler
@RequiredArgsConstructor
public class AddVisitedPageHandler implements CommandHandler<AddVisitedPage, VisitedPage> {

    private final VisitedPageRepository repo;
    private final VisitedPageBuilder visitedPageBuilder;

    @Override
    public VisitedPage handle(AddVisitedPage cmd) {
        VisitedPage visitedPage = repo.save(visitedPageBuilder
                .pageId(cmd.getPageId())
                .userId(cmd.getUserId())
                .build());
        return visitedPage;
    }

}
