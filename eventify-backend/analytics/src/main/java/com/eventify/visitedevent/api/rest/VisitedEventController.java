package com.eventify.visitedevent.api.rest;

import com.eventify.visitedevent.application.commands.CreateVisitedEvent;
import com.eventify.shared.demo.Gate;
import com.eventify.shared.security.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VisitedEventController.BASE_PATH)
@RequiredArgsConstructor
public class VisitedEventController {
    public static final String BASE_PATH = "/visitedEvent";
    private final Gate gate;
    private final Context context;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> confirmHostOnEvent(@RequestBody VisitedEventRequest request) {
        gate.dispatch(CreateVisitedEvent
                .builder()
                .userId(context.getUserId())
                .age(request.getAge())
                .build()
        );
        return ResponseEntity.ok().build();
    }


}
