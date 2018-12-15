package com;

import lombok.*;

/**
 * Created by spasoje on 13-Dec-18.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterUserRequest {
    private String email;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String sex;
    private String role;
}
