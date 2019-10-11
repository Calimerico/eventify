package com.eventify.user.domain;

import com.eventify.shared.ddd.UUIDAggregate;
import com.eventify.shared.demo.Sex;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by spasoje on 13-Dec-18.
 */
@Entity
public class UserAccount extends UUIDAggregate {
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String role;

    private UserAccount() {
    }

    UserAccount(String email, String username, String password, String firstName, String lastName, Sex sex, String role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.role = role;
    }

    public void update(String email, String username, String password, String firstName, String lastName, Sex sex, String role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public String getRole() {
        return role;
    }
}
