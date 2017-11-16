/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Area;

/**
 * @author sergio
 * Bee Smart S.A.S.
 *
 */
public interface AreaDAO {
	public List<Area> getAll() throws Exception;
}
