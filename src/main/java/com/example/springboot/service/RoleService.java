package com.example.springboot.service;

import com.example.springboot.models.Role;

import java.util.Set;

public interface RoleService {
    Role getOrCreateRole(String name);

    Set<Role> getRoles();
}
