/**
 * 
 */
package co.com.sbaqueroa.gads.model;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Area;

/**
 * @author sergio
 * Interface which defines Area Model methods.
 *
 */
public interface AreaInterface {
	/**
	 * Get all Area instances.
	 * @return List of areas.
	 */
	public List<Area> getAll();
	/**Get area by Id.
	 * @param area instance with id defined. 
	 * @return The area with properties defined.
	 */
	public Area getById(Area area);
}
