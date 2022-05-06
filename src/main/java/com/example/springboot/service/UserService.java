package com.example.springboot.service;


import com.example.springboot.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    User getUserById(int id);

    void saveUser(User user);

    void saveUser(User user, String[] roles);

    void updateUser(User updatedUser);

    void updateUser(User updatedUser, String[] roles);

    void delete(int id);
}
