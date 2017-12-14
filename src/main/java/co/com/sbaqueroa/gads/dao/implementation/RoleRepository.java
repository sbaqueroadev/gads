package co.com.sbaqueroa.gads.dao.implementation;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sbaqueroa.gads.model.implementation.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    	Role findByName(String name);
}