package co.com.sbaqueroa.gads.dao.implementation;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sbaqueroa.gads.model.implementation.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}