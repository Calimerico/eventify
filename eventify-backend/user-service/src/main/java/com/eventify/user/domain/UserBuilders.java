package com.eventify.user.domain;

import com.eventify.shared.demo.Sex;
import lombok.Builder;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 03-Feb-19.
 */

public class UserBuilders {

    @Builder(builderMethodName = "aUser")
    private static UserAccount createUser(String email, String username, String password, String firstName, String lastName, Sex sex, String role, Set<UUID> eventIdsThatUserOrganize) {
        UserAccount user = new UserAccount();
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
