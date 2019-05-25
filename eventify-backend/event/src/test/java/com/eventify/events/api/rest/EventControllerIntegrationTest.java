package com.eventify.events.api.rest;

import com.eventify.events.domain.Event;
import com.eventify.events.infrastructure.EventRepository;
import com.eventify.events.infrastructure.KafkaEventProducer;
import com.eventify.shared.config.auth.TestSecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
@Transactional
@WithUserDetails("admin")
public class EventControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventRepository eventRepository;

    @MockBean
    private KafkaEventProducer kafkaEventProducer;

    @After
    public void tearDown() {
        eventRepository.deleteAll();
    }

    @Test
    public void getEventsSuccess() throws Exception {
        //given
        Event event = new Event();
        event.setDescription("desc");
        event.setEventDateTime(LocalDateTime.now());
        event.setEventName("event name");
        event.setEventType("theater");
        eventRepository.save(event);

        //when
        this.mvc.perform(get("/events"))

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.resources.length()", is(1)));
    }

    @Test
    @WithUserDetails("regular")
    public void insertEventTest() throws Exception {
        //given
        doNothing().when(kafkaEventProducer).send(any());
        CreateEventRequest createEventRequest = new CreateEventRequest();
        createEventRequest.setDescription("desc insert");
        createEventRequest.setEventName("name insert");
        createEventRequest.setEventType("theater");
        //when
        this.mvc.perform(post("/events")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createEventRequest)));

        //then
        Event event = eventRepository.findByEventName("name insert");
        assertThat(event).isNotNull();
        assertThat(event.getEventName()).isEqualTo("name insert");
        assertThat(event.getDescription()).isEqualTo("desc insert");
    }

    @Test
    @WithUserDetails("regular")
    public void deleteEventTest() throws Exception {
        //given
        Event event = new Event();
        event.setDescription("desc");
        event.setEventDateTime(LocalDateTime.now());
        event.setEventName("event name");
        event.setEventType("theater");
        eventRepository.save(event);
        doNothing().when(kafkaEventProducer).send(any());

        //when
        this.mvc.perform(delete("/events/{id}",event.getEventId())
                .contentType(APPLICATION_JSON)
        );

        //then
        assertThat(eventRepository.findAll()).hasSize(0);

    }

    @Test
    @WithUserDetails("regular")
    public void updateEventTest() throws Exception {
        //given
        Event event = new Event();
        event.setDescription("desc");
        event.setEventDateTime(LocalDateTime.now());
        event.setEventName("event name");
        event.setEventType("theater");
        eventRepository.save(event);
        UpdateEventRequest updateEventRequest = new UpdateEventRequest();
        String new_desc = "new desc";
        updateEventRequest.setDescription(new_desc);
        LocalDateTime newTime = LocalDateTime.now().minusDays(2);
        updateEventRequest.setEventDateTime(newTime);
        doNothing().when(kafkaEventProducer).send(any());

        //when
        this.mvc.perform(put("/events/{id}", event.getEventId())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateEventRequest)));

        Event updatedEvent = eventRepository.findById(event.getEventId()).orElse(null);
        assertThat(updatedEvent).isNotNull();
        assertThat(updatedEvent.getDescription()).isEqualTo(new_desc);
        assertThat(updatedEvent.getEventDateTime()).isEqualTo(newTime);
        assertThat(updatedEvent.getEventName()).isEqualTo(event.getEventName());
    }
}
