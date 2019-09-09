package com.eventify.user.domain;

import com.eventify.shared.demo.Sex;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spasoje on 13-Dec-18.
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class UserAccount {
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String role;
    @ElementCollection
    private Set<UUID> eventIdsThatUserOrganize;
}
