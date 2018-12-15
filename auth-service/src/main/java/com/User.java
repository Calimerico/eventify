package com;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by spasoje on 13-Dec-18.
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class User {
    private String email;
    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String sex;
    private String role;
}
