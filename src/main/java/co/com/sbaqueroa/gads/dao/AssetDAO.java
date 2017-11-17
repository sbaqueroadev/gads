/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Asset;

/**
 * @author sergio
 * .
 *
 */
public interface AssetDAO {
	public List<Asset> getAll() throws Exception;
	public List<Asset> getAllByFieldValue(String field, String value) throws Exception;
	public Asset insert(Asset asset) throws Exception;
	public Asset update(Asset asset) throws Exception;
}
