package com.example.userapi.service;

import com.example.userapi.model.User;
import com.example.userapi.repository.UserRepository;
import com.example.userapi.util.ExcelFileHandler;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ExcelFileHandler excelFileHandler;

    public UserService(UserRepository userRepository, ExcelFileHandler excelFileHandler) {
        this.userRepository = userRepository;
        this.excelFileHandler = excelFileHandler;
    }

    public User signUpUser(User user) throws Exception {
        // Check if user already exists by email and phone
        if (userRepository.existsByEmailAndPhone(user.getEmail(), user.getPhone())) {
            throw new Exception("User with this Email and Phone already exists");
        }

        User savedUser = userRepository.save(user);  // Save the user in the database
        updateExcelFile();  // Update Excel file with the new user data
        return savedUser;
    }

    public List<User> getAdminRoleDetails() {
        return userRepository.findByRole("admin");
    }

    public List<User> getUserRoleDetails() {
        return userRepository.findByRole("user");
    }

    public List<User> getAllUserDetails() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User user) throws Exception {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setRole(user.getRole());
        existingUser.setAddress(user.getAddress());
        User updatedUser = userRepository.save(existingUser);
        updateExcelFile();  // Update Excel file after the user update
        return updatedUser;
    }

    public void deleteUser(Long id) throws IOException {
        userRepository.deleteById(id);
        updateExcelFile();  // Update Excel file after user deletion
    }

    // Update Excel file with all users
    private void updateExcelFile() throws IOException {
        List<User> allUsers = userRepository.findAll();
        excelFileHandler.writeToExcel(allUsers);
    }


    public Optional<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id); // Assuming userRepository.findById returns a User
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Invalid user Id: " + id);
        }
        return user;
    }



}