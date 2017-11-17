/**
 * 
 */
package co.com.sbaqueroa.gads.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sbaqueroa.gads.dao.PersonDAO;
import co.com.sbaqueroa.gads.model.PersonInterface;
import co.com.sbaqueroa.gads.model.implementation.Person;

/**
 * @author sergio
 * .
 *
 */
@Service
public class PersonImpl implements PersonInterface {

	@Autowired
	private PersonDAO personDAO;
	
	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.model.AreaInterface#getAll()
	 */
	@Override
	public List<Person> getAll() {
		try {
			return personDAO.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Person>();
	}

}
