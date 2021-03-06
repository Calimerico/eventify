package com.eventify.dataseeder.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(value = "id", allowGetters = true)
public class User {
    private int id;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String sex;
    private String role;
}
