package com.eventify.auth.domain;

import lombok.Builder;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 03-Feb-19.
 */

public class UserBuilders {

    @Builder(builderMethodName = "aUser")
    private static User createUser(String email, String username, String password, String firstName, String lastName, String sex, String role, Set<UUID> eventIdsThatUserOrganize) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSex(sex);
        user.setRole(role);
        user.setEventIdsThatUserOrganize(eventIdsThatUserOrganize);
        return user;
    }
}
