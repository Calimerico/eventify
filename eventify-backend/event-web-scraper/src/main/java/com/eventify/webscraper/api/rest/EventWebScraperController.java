package com.eventify.webscraper.api.rest;

import com.eventify.webscraper.application.commands.ScrapEvents;
import com.eventify.shared.demo.Gate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by spasoje on 18-Nov-18.
 */
@RestController
@RequiredArgsConstructor
public class EventWebScraperController {

    private final Gate gate;

    @PostMapping(value = "/scrap")
    public void scrapEvents() throws IOException {
        ArrayList<String> linksToScrap = new ArrayList<>();
        linksToScrap.add("meetup");
        linksToScrap.add("naSceni");
        gate.dispatch(ScrapEvents
                .builder()
                .linksToScrap(linksToScrap)
                .build());
    }
}
