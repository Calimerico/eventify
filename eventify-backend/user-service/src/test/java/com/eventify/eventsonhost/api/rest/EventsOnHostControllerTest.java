package com.eventify.eventsonhost.api.rest;

import com.eventify.eventsonhost.domain.EventsOnHostBuilder;
import com.eventify.shared.demo.Sex;
import com.eventify.shared.security.Context;
import com.eventify.user.domain.UserAccount;
import com.eventify.user.domain.UserBuilder;
import com.eventify.user.infrastructure.UserRepository;
import com.eventify.config.security.PermissionService;
import com.eventify.shared.config.auth.TestSecurityConfig;
import com.eventify.eventsonhost.domain.EventsOnHost;
import com.eventify.eventsonhost.domain.EventsOnHostRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

import static com.eventify.eventsonhost.api.rest.EventsOnHostController.*;
import static com.eventify.shared.config.auth.TestSecurityConfig.REGULAR_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
@ContextConfiguration
@Commit//todo read this https://stackoverflow.com/questions/43519761/replacement-of-transactionconfiguration
@Transactional
public class EventsOnHostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBuilder userBuilder;

    @Autowired
    private EventsOnHostBuilder eventsOnHostBuilder;

    @MockBean
    private PermissionService permissionService;

    @MockBean
    private Context context;

    private UUID eventId = UUID.randomUUID();
    private UUID eventId2 = UUID.randomUUID();
    private UUID userId;
    private UserAccount user;

    @Autowired
    private EventsOnHostRepository eventsOnHostRepository;


    @Before
    public void setUp() throws Exception {
        //todo improve security testing!
        user = userBuilder
                .firstName("Spasoje")
                .sex(Sex.MALE)
                .lastName("Petros")
                .build();
        Mockito.when(context.getUserId()).thenReturn(user.getId());
        Mockito.when(permissionService.hasPermissionToConfirmHostOnEvent(any())).thenReturn(true);
        userId = user.getId();
        userRepository.save(user);
        HashSet<UUID> unconfirmedEvents = new HashSet<>();
        unconfirmedEvents.add(eventId);
        unconfirmedEvents.add(eventId2);
        EventsOnHost eventsOnHost = eventsOnHostBuilder.fromUserAccount(user);
        unconfirmedEvents.forEach(eventsOnHost::addUnconfirmedEvent);
        eventsOnHostRepository.save(eventsOnHost);
    }

    @Test
    @WithUserDetails(REGULAR_USER)
    public void confirmHostOnEventTest() throws Exception {
        //given

        //when
        mockMvc.perform(
                patch(BASE_PATH + "/" + eventId + CONFIRM_HOST)
        );
        //then
        EventsOnHost eventsOnHostAfter = eventsOnHostRepository.findByHostId(userId);
        assertThat(eventsOnHostAfter.getUnconfirmedEvents().contains(eventId2)).isEqualTo(true);
        assertThat(eventsOnHostAfter.getUnconfirmedEvents().contains(eventId)).isEqualTo(false);
        assertThat(eventsOnHostAfter.getConfirmedEvents().contains(eventId2)).isEqualTo(false);
        assertThat(eventsOnHostAfter.getConfirmedEvents().contains(eventId)).isEqualTo(true);
    }

    @Test
    @WithUserDetails(REGULAR_USER)
    public void unconfirmHostOnEventTest() throws Exception {
        //given Add 2 confirmed events and remove all unconfirmed events

        EventsOnHost eventsOnHostBefore = eventsOnHostRepository.findByHostId(userId);
        eventsOnHostBefore.removeEvent(eventId);
        eventsOnHostBefore.removeEvent(eventId2);
        eventsOnHostBefore.addConfirmedEvent(eventId);
        eventsOnHostBefore.addConfirmedEvent(eventId2);
        //when
        mockMvc.perform(
                patch(BASE_PATH + "/" + eventId + UNCONFIRM_HOST)
        );
        //then
        EventsOnHost eventsOnHostAfter = eventsOnHostRepository.findByHostId(userId);
        assertThat(eventsOnHostAfter.getUnconfirmedEvents().contains(eventId2)).isEqualTo(false);
        assertThat(eventsOnHostAfter.getUnconfirmedEvents().contains(eventId)).isEqualTo(true);
        assertThat(eventsOnHostAfter.getConfirmedEvents().contains(eventId2)).isEqualTo(true);
        assertThat(eventsOnHostAfter.getConfirmedEvents().contains(eventId)).isEqualTo(false);
    }

    @Test
    @WithUserDetails(REGULAR_USER)
    public void getEventsForConfirmation() throws Exception {
        mockMvc.perform(get(BASE_PATH + UNCONFIRMED_EVENTS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @After
    public void tearDown() throws Exception {
        eventsOnHostRepository.deleteAll();
    }
}
