/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.AssignedAsset;

/**
 * @author sergio
 * Interface which defines assigned assets DAO methods.
 *
 */
public interface AssignedAssetDAO {
	/** Get all assigned asset entities in Database
	 * @return List of assigned asset.
	 * @throws Exception if occurs.
	 */
	public List<AssignedAsset> getAll() throws Exception;
}
