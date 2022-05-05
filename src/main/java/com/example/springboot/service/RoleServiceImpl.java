package com.example.springboot.service;

import com.example.springboot.dao.RoleDao;
import com.example.springboot.models.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getOrCreateRole(String name) {
        return roleDao.getOrCreateRole(name);
    }

    @Override
    public Set<Role> getRoles() {
        return roleDao.getRoles();
    }
}
