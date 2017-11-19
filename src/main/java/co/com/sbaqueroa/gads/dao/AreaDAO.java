/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Area;

/**
 * @author sergio
 * Interface which defines Area DAO methods.
 *
 */
public interface AreaDAO {
	/** Get all Area Entities in Database
	 * @return List of areas.
	 * @throws Exception if occurs.
	 */
	public List<Area> getAll() throws Exception;
	/**Get area Entity by Id.
	 * @param area Instance with id defined. 
	 * @return The area with this id.
	 * @throws Exception if occurs.
	 */
	public Area getById(Area area) throws Exception;
}
