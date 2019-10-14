package com.eventify.event.api.rest;

import com.eventify.event.domain.Event;
import com.eventify.event.domain.EventBuilder;
import com.eventify.shared.config.auth.IntegrationTest;
import com.eventify.shared.demo.EventType;
import com.eventify.event.domain.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.eventify.event.api.rest.EventController.*;
import static com.eventify.shared.config.auth.TestSecurityConfig.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//todo we should test here if someone can delete or update his own event!
public class EventControllerIntegrationTest extends IntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventBuilder eventBuilder;

    @Test
    public void getEventsSuccess() throws Exception {
        //given
        LocalDateTime eventDateTime = LocalDateTime.now().plusDays(1);
        String name = "event name";
        String desc = "desc";
        EventType theater = EventType.THEATER;
        Event event = eventBuilder
                .description(desc)
                .eventDateTime(eventDateTime)
                .eventName(name)
                .eventType(theater)
                .build();
        eventRepository.save(event);

        //when
        this.mvc.perform(
                get(BASE_PATH)
                        .contentType(APPLICATION_JSON)
        )
                //then
                 .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    jsonPath("$._embedded.resources.length()", is(1));
                    jsonPath("$._embedded.resources[0].description", is(desc));
                    jsonPath("$._embedded.resources[0].eventDateTime", is(eventDateTime));
                    jsonPath("$._embedded.resources[0].eventName", is(name));
                    jsonPath("$._embedded.resources[0].eventType", is(theater));
                });
    }

    @Test
    @WithUserDetails(REGULAR_USER)
    public void insertEventTest() throws Exception {
        insertEvent(200);

        //then
        Event event = eventRepository.findByEventName("name insert");
        assertThat(event).isNotNull();
        assertThat(event.getEventName()).isEqualTo("name insert");
        assertThat(event.getDescription()).isEqualTo("desc insert");
    }

    @Test
    public void cannotInsertEventIfNotAuthorized() throws Exception {
        insertEvent(401);

        //then
        Event event = eventRepository.findByEventName("name insert");
        assertThat(event).isNull();
    }



    @Test
    @WithUserDetails(ADMIN_USER)
    public void deleteEventTest() throws Exception {
        deleteEvent(204);

        //then
        assertThat(eventRepository.findAll()).hasSize(0);
    }

    @Test
    public void cannotDeleteEventIfNotAdminAndItsNotYourEvent() throws Exception {
        deleteEvent(401);

        //then
        assertThat(eventRepository.findAll()).hasSize(1);
    }



    @Test
    @WithUserDetails(ADMIN_USER)
    public void updateEventTest() throws Exception {
        //given
        Event event = eventBuilder
                .description("desc")
                .eventDateTime(LocalDateTime.now().plusDays(1))
                .eventName("event name")
                .eventType(EventType.THEATER)
                .build();
        eventRepository.save(event);
        UpdateEventRequest updateEventRequest = new UpdateEventRequest();
        String new_desc = "new desc";
        updateEventRequest.setDescription(new_desc);
        updateEventRequest.setEventName(event.getEventName());
        updateEventRequest.setEventType(event.getEventType());
        LocalDateTime newTime = LocalDateTime.now().plusDays(2);
        updateEventRequest.setEventDateTime(newTime);

        //when
        this.mvc.perform(put(BASE_PATH + ID_PATH, event.getId())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateEventRequest)));

        Event updatedEvent = eventRepository.loadById(event.getId());
        assertThat(updatedEvent.getDescription()).isEqualTo(new_desc);
        assertThat(updatedEvent.getEventDateTime()).isEqualTo(newTime);
        assertThat(updatedEvent.getEventName()).isEqualTo(event.getEventName());
    }

    @Test
    public void cannotUpdateEventIfNotAdminAndItsNotYourEvent() throws Exception {
        //given
        LocalDateTime eventDateTime = LocalDateTime.now().plusDays(1);
        Event event = eventBuilder
                .description("desc")
                .eventDateTime(eventDateTime)
                .eventName("event name")
                .eventType(EventType.THEATER)
                .build();
        eventRepository.save(event);
        UpdateEventRequest updateEventRequest = new UpdateEventRequest();
        String new_desc = "new desc";
        updateEventRequest.setDescription(new_desc);
        LocalDateTime newTime = LocalDateTime.now().minusDays(2);
        updateEventRequest.setEventDateTime(newTime);

        //when
        this.mvc.perform(put(BASE_PATH + ID_PATH, event.getId())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateEventRequest)));

        Event updatedEvent = eventRepository.loadById(event.getId());
        assertThat(updatedEvent.getDescription()).isEqualTo("desc");
        assertThat(updatedEvent.getEventDateTime()).isEqualTo(eventDateTime);
        assertThat(updatedEvent.getEventName()).isEqualTo("event name");
    }

    private void insertEvent(int expectedStatus) throws Exception {
        //given
        CreateEventRequest createEventRequest = new CreateEventRequest();
        String desc = "desc insert";
        createEventRequest.setDescription(desc);
        String name = "name insert";
        createEventRequest.setEventName(name);
        EventType theater = EventType.THEATER;
        createEventRequest.setEventType(theater);
        LocalDateTime eventDateTime = LocalDateTime.now().plusDays(1);
        createEventRequest.setEventDateTime(eventDateTime);
        //when
        this.mvc.perform(post(BASE_PATH)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createEventRequest)))
                .andExpect(mvcResult -> {
                    assertThat(mvcResult.getResponse().getStatus()).isEqualTo(expectedStatus);
                    //then
                    if (expectedStatus >= 200 && expectedStatus < 300) {
                        jsonPath("$._embedded.resources.length()", is(1));
                        jsonPath("$._embedded.resources[0].description", is(desc));
                        jsonPath("$._embedded.resources[0].eventDateTime", is(eventDateTime));
                        jsonPath("$._embedded.resources[0].eventName", is(name));
                        jsonPath("$._embedded.resources[0].eventType", is(theater));
                    }

                });
    }

    private void deleteEvent(int expectedStatus) throws Exception {
        //given
        Event event = eventBuilder
                .description("desc")
                .eventDateTime(LocalDateTime.now().plusDays(1))
                .eventName("event name")
                .eventType(EventType.THEATER)
                .build();
        eventRepository.save(event);

        //when
        this.mvc.perform(delete(BASE_PATH + ID_PATH,event.getId())
                .contentType(APPLICATION_JSON)
        )
                //then
                .andExpect(mvcResult -> {
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(expectedStatus);
        });
    }
}
