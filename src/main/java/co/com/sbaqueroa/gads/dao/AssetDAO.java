/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Asset;

/**
 * @author sergio
 * Bee Smart S.A.S.
 *
 */
public interface AssetDAO {
	public List<Asset> getAll() throws Exception;
	public List<Asset> getAllByFieldValue(String field, String value) throws Exception;
}
