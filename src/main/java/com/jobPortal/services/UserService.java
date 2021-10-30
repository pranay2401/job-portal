package com.jobPortal.services;

import com.jobPortal.models.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(Long id);

    User insert(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long userId);

    boolean verifyUser(Long userId, Double otp);
}
