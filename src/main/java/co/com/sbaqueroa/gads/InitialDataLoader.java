package co.com.sbaqueroa.gads;



import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.com.sbaqueroa.gads.dao.implementation.ApplicationUserRepository;
import co.com.sbaqueroa.gads.dao.implementation.PrivilegeRepository;
import co.com.sbaqueroa.gads.dao.implementation.RoleRepository;
import co.com.sbaqueroa.gads.model.implementation.ApplicationUser;
import co.com.sbaqueroa.gads.model.implementation.Privilege;
import co.com.sbaqueroa.gads.model.implementation.Role;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
 
	boolean alreadySetup = false;
 
    @Autowired
    private ApplicationUserRepository applicationUserRepository;
  
    @Autowired
    private RoleRepository roleRepository;
  
    @Autowired
    private PrivilegeRepository privilegeRepository;
  
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
  
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
  
        if (alreadySetup)
            return;
        Privilege readPrivilege
          = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
          = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
  
        List<Privilege> adminPrivileges = Arrays.asList(
          readPrivilege, writePrivilege);        
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", (Collection<Privilege>) Arrays.asList(readPrivilege));
 
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Role userRole = roleRepository.findByName("ROLE_USER");
        ApplicationUser user = new ApplicationUser();
        user.setPassword(bCryptPasswordEncoder.encode("test"));
        user.setUsername("test");
        user.setEmail("test@test.com");
        user.setRole(adminRole);
        applicationUserRepository.save(user);
        ApplicationUser user2 = new ApplicationUser();
        user2.setPassword(bCryptPasswordEncoder.encode("test2"));
        user.setUsername("test2");
        user2.setEmail("test2@test.com");
        user.setRole(userRole);
        applicationUserRepository.save(user);
 
        alreadySetup = true;
    }
 
    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
  
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
 
    @Transactional
    private Role createRoleIfNotFound(
      String name, Collection<Privilege> privileges) {
  
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}