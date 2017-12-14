package co.com.sbaqueroa.gads.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.sbaqueroa.gads.dao.implementation.ApplicationUserRepository;
import co.com.sbaqueroa.gads.exceptions.UsernameExistsException;
import co.com.sbaqueroa.gads.model.implementation.ApplicationUser;
import co.com.sbaqueroa.gads.services.ApplicationUserServiceImpl;

@Controller
public class UserController {

	@Autowired
    private ApplicationUserServiceImpl applicationUserServiceImpl;
    
    @RequestMapping(value = "/users/sign-up", method = RequestMethod.POST)
    public String signUp(@RequestParam("user") String userS
    		,@RequestParam("pass") String pass) throws UsernameExistsException {
    	ApplicationUser user = new ApplicationUser();
    	user.setUsername(userS);
    	user.setPassword(pass);
    	applicationUserServiceImpl.registerNewUserAccount(user);
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
