package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.dto.UserDto;
import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.ResponseBody;
import com.charlesfrost.blb.models.User;
import com.charlesfrost.blb.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid UserDto user) {
        User userToSave = userService.mapToUser(user);
        try {
            userService.save(userToSave);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseBody("Suckes!",true));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseBody("nie udało się dodać użytkownika",null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(name = "id") Long id) {
        try {
            User user = userService.getOne(id);
            return ResponseEntity.ok(new ResponseBody("Sukces!", user));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseBody(e.getMessage(),null));
        }
    }
}
