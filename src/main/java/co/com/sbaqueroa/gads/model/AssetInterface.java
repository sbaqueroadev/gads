/**
 * 
 */
package co.com.sbaqueroa.gads.model;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Asset;

/**
 * @author sergio
 * Asset Model interface which defines person operations.
 *
 */
public interface AssetInterface {
	/**
	 * @return Asset Model interface which defines person operations.
	 */
	public List<Asset> getAll();
	/**
	 * @param field Name of property
	 * @param value Value of property
	 * @return The List of Asset entities with this property value defined.
	 */
	public List<Asset> getAllByFieldValue(String field,String value);
	/**
	 * Insert a new Asset instance.
	 * @param asset to be inserted.
	 * @return the asset inserted successfully.
	 */
	public boolean add(Asset asset);
	/**
	 * Update an existing Asset
	 * @param asset to be updated.
	 * @return the asset updated successfully.
	 */
	public boolean update(Asset asset);
}
