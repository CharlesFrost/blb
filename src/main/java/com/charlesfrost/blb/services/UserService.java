package com.charlesfrost.blb.services;

import com.charlesfrost.blb.dto.UserDto;
import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.User;
import com.charlesfrost.blb.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        User existing = userRepository.findByUsername(user.getUsername());
        if (existing==null) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Użytkownik o takiej nazwie już istnieje!");
        }
    }

    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setAvatarUrl(userDto.getAvatarUrl());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPasswordMatcher(userDto.getPasswordMatcher());
        return user;
    }

    public User findById(Long authorId) {
        return userRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Nie ma takiego autora!"));
    }
}
