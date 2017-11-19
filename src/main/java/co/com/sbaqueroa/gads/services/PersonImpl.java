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
 * Person services.
 */
@Service
public class PersonImpl implements PersonInterface {

	/**
	 * Connection with DAO.
	 */
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

	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gads.model.PersonInterface#getById(co.com.sbaqueroa.gads.model.implementation.Person)
	 */
	@Override
	public Person getById(Person person) {
		try {
			return personDAO.getById(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
