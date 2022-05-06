package com.example.springboot.dao;

import com.example.springboot.models.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role getOrCreateRole(String name) {
        Query query = em.createQuery("select r from Role r where r.name  = :name", Role.class);
        query.setParameter("name", name);
        if (((org.hibernate.query.Query) query).list().isEmpty()) {
            Role role = new Role(name);
            em.persist(role);
            return role;
        } else {
            return (Role) query.getSingleResult();
        }
    }

    @Override
    public Set<Role> getRoles() {
        return em.createQuery("SELECT r from Role r", Role.class)
                .getResultStream().collect(Collectors.toSet());
    }

}
