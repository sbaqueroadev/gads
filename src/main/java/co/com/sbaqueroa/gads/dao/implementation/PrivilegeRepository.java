package co.com.sbaqueroa.gads.dao.implementation;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sbaqueroa.gads.model.implementation.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}