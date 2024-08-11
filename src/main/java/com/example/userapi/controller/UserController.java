package com.example.userapi.controller;

import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@Valid @RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("User registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admins")
    public List<User> getAdminRoleDetails() {
        return userService.getUsersByRole("admin");
    }

    @GetMapping("/users")
    public List<User> getUserRoleDetails() {
        return userService.getUsersByRole("user");
    }

    @GetMapping
    public List<User> getAllUserDetails() {
        return userService.getAllUsers();
    }
}
