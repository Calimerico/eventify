package com.eventify.event.api.rest;

import com.eventify.event.domain.Event;
import com.eventify.event.domain.EventBuilder;
import com.eventify.shared.demo.EventType;
import com.eventify.event.domain.EventRepository;
import com.eventify.shared.config.auth.TestSecurityConfig;
import com.eventify.shared.kafka.KafkaEventProducer;
import com.eventify.shared.kafka.KafkaStreams;
import com.eventify.shared.kafka.MessageChannelFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.eventify.event.api.rest.EventController.*;
import static com.eventify.shared.config.auth.TestSecurityConfig.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
@Transactional
//todo we should test here if someone can delete or update his own event!
public class EventControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventBuilder eventBuilder;

    @MockBean
    private KafkaEventProducer kafkaEventProducer;

    @MockBean
    private MessageChannelFactory messageChannelFactory;//todo think of better way of mocking kafka than this 5 MockBeans

    @MockBean
    private KafkaStreams kafkaStreams;

    @MockBean(name = "eventsTopicInputChannel")
    private SubscribableChannel eventsTopicInputChannel;

    @MockBean(name = "placesTopicInputChannel")
    private SubscribableChannel placesTopicInputChannel;

    @After
    public void tearDown() {
        eventRepository.deleteAll();
    }

    @Test
    public void getEventsSuccess() throws Exception {
        //given
        Event event = eventBuilder
                .description("desc")
                .eventDateTime(LocalDateTime.now().plusDays(1))
                .eventName("event name")
                .eventType(EventType.THEATER)
                .build();
        eventRepository.save(event);

        //when
        this.mvc.perform(
                get(BASE_PATH)
                        .contentType(APPLICATION_JSON)
        )

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.resources.length()", is(1)));
    }

    @Test
    @WithUserDetails(REGULAR_USER)
    public void insertEventTest() throws Exception {
        insertEvent();

        //then
        Event event = eventRepository.findByEventName("name insert");
        assertThat(event).isNotNull();
        assertThat(event.getEventName()).isEqualTo("name insert");
        assertThat(event.getDescription()).isEqualTo("desc insert");
    }

    @Test
    public void cannotInsertEventIfNotAuthorized() throws Exception {
        insertEvent();

        //then
        Event event = eventRepository.findByEventName("name insert");
        assertThat(event).isNull();
    }



    @Test
    @WithUserDetails(ADMIN_USER)
    public void deleteEventTest() throws Exception {
        deleteEvent();

        //then
        assertThat(eventRepository.findAll()).hasSize(0);
    }

    @Test
    public void cannotDeleteEventIfNotAdminAndItsNotYourEvent() throws Exception {
        deleteEvent();

        //then
        assertThat(eventRepository.findAll()).hasSize(1);
    }



    @Test
    @WithUserDetails(ADMIN_USER)
    public void updateEventTest() throws Exception {
        //given
        Event event = eventBuilder
                .description("desc")
                .eventDateTime(LocalDateTime.now())
                .eventName("event name")
                .eventType(EventType.THEATER)
                .build();
        eventRepository.save(event);
        UpdateEventRequest updateEventRequest = new UpdateEventRequest();
        String new_desc = "new desc";
        updateEventRequest.setDescription(new_desc);
        updateEventRequest.setEventName(event.getEventName());
        updateEventRequest.setEventType(event.getEventType());
        LocalDateTime newTime = LocalDateTime.now().minusDays(2);
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

    private void insertEvent() throws Exception {
        //given
        CreateEventRequest createEventRequest = new CreateEventRequest();
        createEventRequest.setDescription("desc insert");
        createEventRequest.setEventName("name insert");
        createEventRequest.setEventType(EventType.THEATER);
        //when
        this.mvc.perform(post(BASE_PATH)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createEventRequest)));
    }

    private void deleteEvent() throws Exception {
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
        );
    }
}
