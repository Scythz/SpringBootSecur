package com.example.springboot.dao;

import com.example.springboot.models.Role;

import java.util.Set;

public interface RoleDao {
    Role getOrCreateRole(String name);

    Set<Role> getRoles();


}
