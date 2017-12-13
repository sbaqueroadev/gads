package co.com.sbaqueroa.gads.webservices;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.com.sbaqueroa.gads.model.implementation.Person;
import co.com.sbaqueroa.gads.services.PersonImpl;

/**
 * Handles requests for the application in person section.
 */
@Controller
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	/**
	 * Connection with services layer.
	 */
	@Autowired
	private PersonImpl personImpl;
	
	/**
	 * @return All persons in JSON Format.
	 */
	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> home() {
		List<Person> result = personImpl.getAll();
		if(result.size()>0)
			return ResponseEntity.ok(result);
		else{
			return ResponseEntity.status(404).body(new JSONObject()
					.put("result", "fail")
					.put("msg", "No data").toString());
		}
	}
	
	/**
	 * @return Person record View represented by a JSP file.
	 */
	@RequestMapping(value="/person/home",method = {RequestMethod.GET})
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("person/record");
		List<Person> data = personImpl.getAll();
		if(data.size()>0)
			mv.addObject("data",data);
		return mv;
	}
	
	/**Get person by id.
	 * @param id to look for.
	 * @return Person instance identified by id.
	 */
	public Person get(int id) {
		Person person = new Person();
		person.setId(id);
		return personImpl.getById(person);
	}

}
