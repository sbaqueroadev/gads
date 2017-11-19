/**
 * 
 */
package co.com.sbaqueroa.gads.model;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.City;

/**
 * @author sergio
 * City Model interface which defines person operations.
 *
 */
public interface CityInterface {
	/**
	 * @return List of cities connecting.
	 */
	public List<City> getAll();
}
