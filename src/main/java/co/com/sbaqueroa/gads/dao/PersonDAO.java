/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Person;

/**
 * @author sergio
 * .
 *
 */
public interface PersonDAO {
	public List<Person> getAll() throws Exception;
	public Person getById(Person person) throws Exception;
}
