package com.eventify.visitedpage.api.rest;


import com.eventify.shared.demo.Gate;
import com.eventify.shared.security.Context;
import com.eventify.visitedpage.application.commands.AddVisitedPage;
import com.eventify.visitedpage.domain.VisitedPage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static com.eventify.shared.security.RoleName.ROLE_ADMIN;
import static com.eventify.shared.security.RoleName.ROLE_REGISTERED_USER;


@RestController
@RequestMapping(value = VisitedPageController.BASE_PATH)
@RequiredArgsConstructor
public class VisitedPageController {

    static final String BASE_PATH = "/analytics";
    private final Gate gate;
    private final Context context;
//todo add test
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured({ROLE_REGISTERED_USER, ROLE_ADMIN})
    public ResponseEntity<VisitedPageResource> visitedPage(@RequestBody @Valid VisitedPageRequest req) {
        //todo validate
        VisitedPage visitedPage = gate.dispatch(AddVisitedPage
                .builder()
                .pageId(req.getPageId())
                .userId(req.getUserId())
                .build());
        return ResponseEntity.ok(VisitedPageResource.fromEvent(visitedPage));
    }
}
