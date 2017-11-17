/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Area;

/**
 * @author sergio
 * .
 *
 */
public interface AreaDAO {
	public List<Area> getAll() throws Exception;
}
