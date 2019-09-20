package com.eventify.user.api.rest;

import lombok.*;

/**
 * Created by spasoje on 13-Dec-18.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
class RegisterUserRequest {
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String sex;
    private String role;
}
