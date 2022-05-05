package com.example.springboot.dao;

import com.example.springboot.models.User;

import java.util.List;

public interface UserDao {
    User findByUsername(String username);

    List<User> getAllUsers();

    User getUserById(int id);

    void saveUser(User user);

    void saveUser(User user, String[] roles);

    void updateUser(int id, User updatedUser);

    void delete(int id);

}
