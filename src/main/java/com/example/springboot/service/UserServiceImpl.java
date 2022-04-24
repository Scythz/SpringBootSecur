package com.example.springboot.service;

import com.example.springboot.dao.UserRepository;
import com.example.springboot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);

    }

    @Override
    public void updateUser(int id, User updatedUser) {
        updatedUser.setId(getUserById(id).getId());
        userRepository.save(updatedUser);

    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
