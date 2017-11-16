/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Person;

/**
 * @author sergio
 * Bee Smart S.A.S.
 *
 */
public interface PersonDAO {
	public List<Person> getAll() throws Exception;
}
