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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Size(max = 64, min = 2)
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 8,message = "Hasło musi zawierać conajmniej 8 znaków!")
    @JsonIgnore
    private String password;
    @Transient
    @JsonIgnore
    private String passwordMatcher;
    private String avatarUrl;
    @ValidateEmail
    @JsonIgnore
    private String email;

    public User() {
    }

    public User(@Size(max = 64, min = 2) @NotBlank String username, @Size(max = 64, min = 2) @NotBlank String password, String passwordMatcher, String avatarUrl, String email) {
        this.username = username;
        this.password = password;
        this.passwordMatcher = passwordMatcher;
        this.avatarUrl = avatarUrl;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
