package com.example.userapi.service;

import com.example.userapi.model.User;
import com.example.userapi.repository.UserRepository;
import com.example.userapi.util.ExcelFileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExcelFileHandler excelFileHandler;

    public User signUpUser(User user) throws Exception {
        if (userRepository.existsByEmailAndPhone(user.getEmail(), user.getPhone())) {
            throw new Exception("User with this Email and Phone already exists");
        }
        User savedUser = userRepository.save(user);
        updateExcelFile();
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
        updateExcelFile();
        return updatedUser;
    }

    public void deleteUser(Long id) throws IOException {
        userRepository.deleteById(id);
        updateExcelFile();
    }

    private void updateExcelFile() throws IOException {
        List<User> allUsers = userRepository.findAll();
        excelFileHandler.writeToExcel(allUsers);
    }
}