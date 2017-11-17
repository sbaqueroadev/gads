/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.AssignedAsset;

/**
 * @author sergio
 * .
 *
 */
public interface AssignedAssetDAO {
	public List<AssignedAsset> getAll() throws Exception;
}
