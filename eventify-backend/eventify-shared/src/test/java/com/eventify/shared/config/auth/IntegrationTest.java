package com.eventify.shared.config.auth;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {TestSecurityConfig.class, MockKafkaConfig.class})
@AutoConfigureMockMvc
@Transactional
public class IntegrationTest {
}
