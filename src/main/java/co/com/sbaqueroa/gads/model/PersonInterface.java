/**
 * 
 */
package co.com.sbaqueroa.gads.model;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Person;

/**
 * @author sergio
 * Person Model interface which defines person operations.
 */
public interface PersonInterface {
	/**
	 * @return List of persons connecting.
	 */
	public List<Person> getAll();
	/**
	 * @param person with just id defined.
	 * @return Person with all properties defined.
	 */
	public Person getById(Person person);
}
