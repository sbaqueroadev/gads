/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Asset;
import co.com.sbaqueroa.gads.model.implementation.City;

/**
 * @author sergio
 * Interface which defines City DAO methods.
 *
 */
public interface CityDAO {
	/** Get all city entities in Database
	 * @return List of cities.
	 * @throws Exception if occurs.
	 */
	public List<City> getAll() throws Exception;
}
