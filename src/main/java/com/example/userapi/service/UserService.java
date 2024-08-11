package com.example.userapi.service;
import com.example.userapi.model.User;
import com.example.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save user with validation
    public User saveUser(User user) {
        Optional<User> existingUser = userRepository.findByEmailAndPhone(user.getEmail(), user.getPhone());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with this email and phone already exists");
        }
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get users by role
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
}
