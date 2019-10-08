package com.eventify.user.domain;

import com.eventify.shared.DomainEventPublisher;
import com.eventify.shared.demo.Sex;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 03-Feb-19.
 */

@Component
public class UserBuilder {

    @Autowired
    private DomainEventPublisher domainEventPublisher;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Sex sex;
    private String role;

    UserBuilder() {
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public UserBuilder role(String role) {
        this.role = role;
        return this;
    }

    public UserAccount build() {
        return new UserAccount(email, username, password, firstName, lastName, sex, role, domainEventPublisher);
    }
}
