package com.eventify.userban.api.rest;

import com.eventify.shared.config.auth.IntegrationTest;
import com.eventify.shared.demo.Sex;
import com.eventify.user.domain.UserAccount;
import com.eventify.user.domain.UserBuilder;
import com.eventify.user.infrastructure.UserRepository;
import com.eventify.userban.domain.UserBanInfo;
import com.eventify.userban.infrastructure.UserBanRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.eventify.shared.config.auth.TestSecurityConfig.*;
import static com.eventify.userban.api.rest.UserBanController.BAN_USER;
import static com.eventify.userban.api.rest.UserBanController.BASE_PATH;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class UserBanControllerIntegrationTest extends IntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBanRepository userBanRepository;

    @Autowired
    private UserBuilder userBuilder;

    private UserAccount user;

    @Before
    public void setUp() {
        user = userBuilder
                .firstName("Spasoje")
                .lastName("Petronijevic")
                .username("spasoje")
                .password("spasoje")
                .sex(Sex.MALE)
                .build();
        userRepository.save(user);
    }

    @Test
    @WithUserDetails(ADMIN_USER)
    public void banUserTest() throws Exception {
        banUser(200);

        //then
        UserBanInfo userBanInfo = userBanRepository.findById(user.getId()).orElse(null);
        assertThat(userBanInfo).isNotNull();
        assertThat(userBanInfo.getBanInfos().size()).isEqualTo(1);
    }

    @Test
    @WithUserDetails(REGULAR_USER)
    public void cannotBanUserIfYouAreNotAdmin() throws Exception {
        banUser(403);
    }

    private void banUser(int expectedStatus) throws Exception {
        //given
        BanUserRequest banUserRequest = new BanUserRequest();
        UUID adminId = UUID.randomUUID();
        LocalDateTime bannedUntil = LocalDateTime.of(2020, 1, 1, 1, 1);
        banUserRequest.setAdminId(adminId);
        banUserRequest.setBannedUntil(bannedUntil);
        banUserRequest.setReasonForBan("Bad behaviour");
        banUserRequest.setUserId(user.getId());

        //when
        mvc.perform(patch(BASE_PATH + BAN_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(banUserRequest))
        ).andExpect(mvcResult -> assertThat(mvcResult.getResponse().getStatus()).isEqualTo(expectedStatus));


    }

}
