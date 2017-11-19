package co.com.sbaqueroa.gads.webservices;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.com.sbaqueroa.gads.model.implementation.Person;
import co.com.sbaqueroa.gads.services.PersonImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonImpl personImpl;
	
	/**
	 * Redirects to /order/form as the home page.
	 * 
	 * @param request HTTP request to handle on.
	 * @param httpServletResponse HTTP response to return.
	 * 
	 * @return View represented by a JSP file.
	 */
	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public @ResponseBody List<Person> home() {
		return personImpl.getAll();
	}
	
	@RequestMapping(value="/person/home",method = {RequestMethod.GET})
	public ModelAndView form(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("person/record");
		List<Person> data = this.home();
		if(data.size()>0)
			mv.addObject("data",data);
		return mv;
	}
	
	public Person get(int id) {
		Person person = new Person();
		person.setId(id);
		return personImpl.getById(person);
	}

}
