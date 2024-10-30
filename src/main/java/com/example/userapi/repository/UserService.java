package com.example.userapi.repository;

import com.example.userapi.model.User;

import java.util.Optional;
//import com.google.common.base.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    void signUpUser(User user);
    void updateUser(Long id, User user);
}
