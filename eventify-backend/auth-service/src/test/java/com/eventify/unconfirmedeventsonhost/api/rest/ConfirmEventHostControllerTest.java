package com.eventify.unconfirmedeventsonhost.api.rest;

import com.eventify.user.domain.UserAccount;
import com.eventify.user.domain.UserBuilders;
import com.eventify.user.infrastructure.UserRepository;
import com.eventify.config.security.PermissionService;
import com.eventify.shared.config.auth.TestSecurityConfig;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHost;
import com.eventify.unconfirmedeventsonhost.domain.UnconfirmedEventsOnHostRepository;
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

import static com.eventify.shared.config.auth.TestSecurityConfig.REGULAR_USER;
import static com.eventify.unconfirmedeventsonhost.api.rest.ConfirmEventHostController.BASE_PATH;
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
public class ConfirmEventHostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private PermissionService permissionService;

    private UUID eventId = UUID.randomUUID();
    private UUID eventId2 = UUID.randomUUID();
    private UUID userId;

    @Autowired
    private UnconfirmedEventsOnHostRepository unconfirmedEventsOnHostRepository;

    @Before
    public void setUp() throws Exception {
        //todo improve security testing!
        Mockito.when(permissionService.hasPermissionToConfirmHostOnEvent(any())).thenReturn(true);
        UserAccount user = UserBuilders
                .aUser()
                .firstName("Spasoje")
                .sex("MALE")
                .lastName("Petros")
                .build();
        userId = user.getId();
        userRepository.save(user);
        HashSet<UUID> unconfirmedEvents = new HashSet<>();
        unconfirmedEvents.add(eventId);
        unconfirmedEvents.add(eventId2);
        unconfirmedEventsOnHostRepository.save(UnconfirmedEventsOnHost
                .builder()
                .user(user)
                .unconfirmedEvents(unconfirmedEvents)
                .build()
        );
    }

    @Test
    @WithUserDetails(REGULAR_USER)
    public void confirmHostOnEventTest() throws Exception {
        //given
        //when
        mockMvc.perform(
                patch(BASE_PATH + "/" + eventId)
        );
        //then
        UnconfirmedEventsOnHost unconfirmedEventsOnHost = unconfirmedEventsOnHostRepository.findByUserId(userId);
        assertThat(unconfirmedEventsOnHost.getUnconfirmedEvents().contains(eventId2)).isEqualTo(true);
        assertThat(unconfirmedEventsOnHost.getUnconfirmedEvents().contains(eventId)).isEqualTo(false);
    }

    @Test
    @WithUserDetails(REGULAR_USER)
    public void getEventsForConfirmation() throws Exception {
        mockMvc.perform(get(BASE_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @After
    public void tearDown() throws Exception {
        unconfirmedEventsOnHostRepository.deleteAll();
    }
}
