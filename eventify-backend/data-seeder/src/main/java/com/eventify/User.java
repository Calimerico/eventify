package com.eventify;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private String email;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String sex;
    private String role;
}
