/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Person;

/**
 * @author sergio
 * Interface which defines Person DAO methods.
 *
 */
public interface PersonDAO {
	/** Get all person Entities in Database
	 * @return List of persons.
	 * @throws Exception if occurs.
	 */
	public List<Person> getAll() throws Exception;
	/**Get person Entity by Id.
	 * @param person Instance with id defined. 
	 * @return The person with this id.
	 * @throws Exception if occurs.
	 */
	public Person getById(Person person) throws Exception;
}
