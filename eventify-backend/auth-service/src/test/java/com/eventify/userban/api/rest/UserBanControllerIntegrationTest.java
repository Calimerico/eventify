package com.eventify.userban.api.rest;

import com.eventify.auth.domain.UserAccount;
import com.eventify.auth.domain.UserBuilders;
import com.eventify.auth.infrastructure.UserRepository;
import com.eventify.shared.config.auth.TestSecurityConfig;
import com.eventify.userban.domain.UserBanInfo;
import com.eventify.userban.infrastructure.UserBanRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.eventify.shared.config.auth.TestSecurityConfig.*;
import static com.eventify.userban.api.rest.UserBanController.BASE_PATH;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
@ContextConfiguration
@Commit//todo read this https://stackoverflow.com/questions/43519761/replacement-of-transactionconfiguration
@Transactional
public class UserBanControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBanRepository userBanRepository;

    private UserAccount user = UserBuilders
            .aUser()
            .firstName("Spasoje")
            .lastName("Petronijevic")
            .username("spasoje")
            .password("spasoje")
            .sex("MALE")
            .build();

    @Before
    public void setUp() {
        userRepository.save(user);
    }

    @Test
    @WithUserDetails(ADMIN_USER)
    public void banUserTest() throws Exception {
        //given
        BanUserRequest banUserRequest = new BanUserRequest();
        UUID adminId = UUID.randomUUID();
        LocalDateTime bannedUntil = LocalDateTime.of(2020, 1, 1, 1, 1);
        banUserRequest.setAdminId(adminId);
        banUserRequest.setBannedUntil(bannedUntil);
        banUserRequest.setReasonForBan("Bad behaviour");
        banUserRequest.setUserId(user.getId());

        //when
        mvc.perform(post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(banUserRequest))
        );

        //then
        UserBanInfo userBanInfo = userBanRepository.findById(user.getId()).orElse(null);
        assertThat(userBanInfo).isNotNull();
        assertThat(userBanInfo.getBanInfos().size()).isEqualTo(1);
    }

    @After
    public void tearDown() {
        userBanRepository.deleteAll();
        userRepository.deleteAll();
    }
}
