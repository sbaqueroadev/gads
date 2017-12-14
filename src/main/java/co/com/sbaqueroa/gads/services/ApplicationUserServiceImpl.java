package co.com.sbaqueroa.gads.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.com.sbaqueroa.gads.dao.implementation.ApplicationUserRepository;
import co.com.sbaqueroa.gads.dao.implementation.RoleRepository;
import co.com.sbaqueroa.gads.exceptions.UsernameExistsException;
import co.com.sbaqueroa.gads.model.ApplicationUserInterface;
import co.com.sbaqueroa.gads.model.implementation.ApplicationUser;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserInterface {
	
	private ApplicationUserRepository applicationUserRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private RoleRepository roleRepository;

	public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public ApplicationUser registerNewUserAccount(ApplicationUser applicationUser) throws UsernameExistsException {
		if (usernameExist(applicationUser.getUsername())) {
	        throw new UsernameExistsException
	          ("There is an account with that username: " + applicationUser.getUsername());
	    }
	    ApplicationUser user = new ApplicationUser();
	 
	    user.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
	    user.setEmail(applicationUser.getEmail());
	 
	    user.setRole(roleRepository.findByName("ROLE_USER"));
	    return applicationUserRepository.save(user);
	}

	private boolean usernameExist(String username) {
		if( applicationUserRepository.findByUsername(username) != null ){
			return true;
		}else{
			return false;
		}
	}

	
}
