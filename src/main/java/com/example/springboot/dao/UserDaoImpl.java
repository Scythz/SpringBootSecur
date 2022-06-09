package com.example.springboot.dao;

import com.example.springboot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final RoleDao roleDao;
    @PersistenceContext
    private EntityManager em;
    private final PasswordEncoder pw;

    public UserDaoImpl(RoleDao roleDao, PasswordEncoder pw) {
        this.roleDao = roleDao;
        this.pw = pw;
    }

    @Override
    public User findByUsername(String username) {
        Query query = em.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void saveUser(User user, String[] roles) {
        for (String role : roles) {
            user.addRole(roleDao.getOrCreateRole(role));
        }
        saveUser(user);
    }

    @Override
    public void updateUser(User updatedUser) {
        em.merge(updatedUser);
    }

    @Override
    public void updateUser(User updatedUser, String[] roles) {
        for (String role : roles) {
            updatedUser.addRole(roleDao.getOrCreateRole(role));
        }
        String pass = pw.encode(updatedUser.getPassword());
        updatedUser.setPassword(pass);
        updateUser(updatedUser);
    }

    @Override
    public void delete(int id) {
        em.remove(em.find(User.class, id));
    }
}
