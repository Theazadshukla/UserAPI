package com.example.userapi.Controller;

import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<User> signUpUser(@Valid @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.signUpUser(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/admin")
    @Operation(summary = "Get Admin Role Detail", description = "Get Admin Role through users detail")
    public ResponseEntity<List<User>> getAdminRoleDetails() {
        return ResponseEntity.ok(userService.getAdminRoleDetails());
    }

    @GetMapping("/user")
    @Operation(summary = "Get User Role Detail", description = "Get User Role through users detail")
    public ResponseEntity<List<User>> getUserRoleDetails() {
        return ResponseEntity.ok(userService.getUserRoleDetails());
    }

    @GetMapping("/data")
    @Operation(summary = "Get All Users Detail", description = "Get all users' details")
    public ResponseEntity<List<User>> getAllUserDetails() {
        return ResponseEntity.ok(userService.getAllUserDetails());
    }

    @Operation(summary = "Update a user by ID", description = "Updates the details of an existing user")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "ID of the user to update", example = "1") @PathVariable Long id,
            @Valid @RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @Operation(summary = "Delete a user by ID", description = "Deletes a user by their unique ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user to delete", example = "1") @PathVariable Long id) throws IOException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
