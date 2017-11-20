package co.com.sbaqueroa.gads.webservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.sbaqueroa.gads.dao.implementation.ApplicationUserRepository;
import co.com.sbaqueroa.gads.model.implementation.ApplicationUser;

@Controller
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/users/sign-up", method = RequestMethod.GET)
    public String signUp(@RequestParam("user") String userS
    		,@RequestParam("pass") String pass) {
    	ApplicationUser user = new ApplicationUser();
    	user.setUsername(userS);
    	user.setPassword(pass);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
        return "redirect:access";
    }
    
    /**
	 * @return Home View represented by a JSP file.
	 */
	@RequestMapping(value = "/users/access", method = RequestMethod.GET)
	public String home() {
		return "users/login";
	}
	
	/**
	 * @return Home View represented by a JSP file.
	 */
	@RequestMapping(value = "/users/create", method = RequestMethod.GET)
	public String create() {
		return "users/signUp";
	}
}
