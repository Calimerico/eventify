package com.eventify.auth.domain;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 13-Dec-18.
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)//TODO Getters setters and constructor should be package level?I should create userbuilder
public class User {
    private String email;
    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String sex;
    private String role;
    @ElementCollection
    private Set<UUID> eventIdsThatUserOrganize;
}
