/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Asset;
import co.com.sbaqueroa.gads.model.implementation.City;

/**
 * @author sergio
 * .
 *
 */
public interface CityDAO {
	public List<City> getAll() throws Exception;
}