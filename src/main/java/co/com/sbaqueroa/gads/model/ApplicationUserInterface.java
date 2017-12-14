package co.com.sbaqueroa.gads.model;

import co.com.sbaqueroa.gads.exceptions.UsernameExistsException;
import co.com.sbaqueroa.gads.model.implementation.ApplicationUser;

public interface ApplicationUserInterface {

	public ApplicationUser registerNewUserAccount(ApplicationUser applicationUser) throws UsernameExistsException;
}
