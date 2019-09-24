package com.charlesfrost.blb.models;

import com.charlesfrost.blb.validators.PasswordMatches;
import com.charlesfrost.blb.validators.ValidateEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "users")
@PasswordMatches
public class User extends Person{
    @Size(max = 64, min = 2)
    @NotBlank
    private String username;
    @Size(max = 64, min = 2)
    @NotBlank
    private String password;
    @Transient
    @JsonIgnore
    private String passwordMatcher;
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPasswordMatcher() {
        return passwordMatcher;
    }

    public void setPasswordMatcher(String passwordMatcher) {
        this.passwordMatcher = passwordMatcher;
    }

    @ValidateEmail
    private String email;

    public User() {
    }


    public User(@Size(min = 2, max = 64) @NotBlank String firstName, @Size(min = 2, max = 64) @NotBlank String lastName, @NotNull LocalDate birthDate, @Size(max = 64, min = 2) @NotBlank String username, @Size(max = 64, min = 2) @NotBlank String password, String email) {
        super(firstName, lastName, birthDate);
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
