/**
 * 
 */
package co.com.sbaqueroa.gads.model;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Asset;

/**
 * @author sergio
 * .
 *
 */
public interface AssetInterface {
	public List<Asset> getAll();
	public List<Asset> getAllByFieldValue(String field,String value);
	public boolean add(Asset asset);
	public boolean update(Asset asset);
}
