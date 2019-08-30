package com.eventify.shared.config.auth;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static java.util.Arrays.asList;

@TestConfiguration
public class TestSecurityConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User adminUser = new UserImpl("admin", "password", asList(
                new SimpleGrantedAuthority("ROLE_ADMIN")
        ));
        User regularUser = new UserImpl("regular", "password", asList(
                new SimpleGrantedAuthority("ROLE_REGISTERED_USER")
        ));
        return new InMemoryUserDetailsManager(asList(
                adminUser,
                regularUser
        ));
    }
}
