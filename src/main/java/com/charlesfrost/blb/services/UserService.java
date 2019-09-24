package com.charlesfrost.blb.services;

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
        return userRepository.save(user);
    }

}
