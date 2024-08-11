package com.example.userapi.controller;

import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Operations related to user management")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    @Operation(summary = "Create a new user", description = "Creates a new user with the given details")
    public ResponseEntity<User> signUpUser(@Valid @RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.signUpUser(user));
    }

    @GetMapping("/admin")
    @Operation(summary = "Get  Admin Role Detail", description = "Get Admin Role  through  users detail")
    public ResponseEntity<List<User>> getAdminRoleDetails() {

        return ResponseEntity.ok(userService.getAdminRoleDetails());

    }

    @GetMapping("/user")
    @Operation(summary = "Get  User Role Detail", description = "Get User Role  through  users detail")
    public ResponseEntity<List<User>> getUserRoleDetails() {
        return ResponseEntity.ok(userService.getUserRoleDetails());
    }

    @GetMapping("/data")
    @Operation(summary = "Get  All Users Detail", description = "Get data users detail")
    public ResponseEntity<List<User>> getAllUserDetails() {
        return ResponseEntity.ok(userService.getAllUserDetails());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modify  User Details ", description = "Modify  user details by id ")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete  User Detail", description = "Delete user  detail by id")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws IOException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}