package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.models.User;
import com.charlesfrost.blb.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid User user) {
        User userToSave = userService.save(user);
        return ResponseEntity.ok(userToSave);
    }
}
