package com.charlesfrost.blb.dto;

import com.charlesfrost.blb.validators.PasswordMatches;
import com.charlesfrost.blb.validators.ValidateEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordMatches
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Size(max = 64, min = 3, message = "Nazwa użytkownika musi się składać z conajmniej 3 znaków")
    @NotBlank(message = "Nazwa użytkownika nie może być pusta!")
    private String username;
    @NotBlank(message = "Hasło nie może być puste!")
    @Size(min = 8,message = "Hasło musi zawierać conajmniej 8 znaków!")
    private String password;
    private String passwordMatcher;
    private String avatarUrl;
    @ValidateEmail
    private String email;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordMatcher() {
        return passwordMatcher;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getEmail() {
        return email;
    }
}
