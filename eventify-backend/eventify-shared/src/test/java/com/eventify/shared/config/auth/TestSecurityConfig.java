package com.eventify.shared.config.auth;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.eventify.shared.security.RoleName.ROLE_ADMIN;
import static com.eventify.shared.security.RoleName.ROLE_REGISTERED_USER;
import static java.util.Arrays.asList;

@TestConfiguration
public class TestSecurityConfig {

    public static final String REGULAR_USER = "regular";
    public static final String ADMIN_USER = "admin";
    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User adminUser = new UserImpl(ADMIN_USER, "password", asList(
                new SimpleGrantedAuthority(ROLE_ADMIN)
        ));
        User regularUser = new UserImpl(REGULAR_USER, "password", asList(
                new SimpleGrantedAuthority(ROLE_REGISTERED_USER)
        ));
        return new InMemoryUserDetailsManager(asList(
                adminUser,
                regularUser
        ));
    }
}
