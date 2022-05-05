package com.example.springboot.service;

import com.example.springboot.dao.RoleDao;
import com.example.springboot.dao.UserDao;
import com.example.springboot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(PasswordEncoder bCryptPasswordEncoder, UserDao userDao, RoleDao roleDao) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void saveUser(User user, String[] roles) {
        userDao.saveUser(user,roles);
    }


    @Override
    @Transactional
    public void updateUser(int id, User user) {
        if (user.getPassword() == null) {
            user.setPassword(getUserById(user.getId()).getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userDao.saveUser(user);

    }

    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
