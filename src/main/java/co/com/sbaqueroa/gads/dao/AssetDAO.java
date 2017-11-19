/**
 * 
 */
package co.com.sbaqueroa.gads.dao;

import java.util.List;

import co.com.sbaqueroa.gads.model.implementation.Asset;

/**
 * @author sergio
 * Interface which defines Asset DAO methods.
 *
 */
public interface AssetDAO {
	/** Get all asset entities in Database
	 * @return List of asset.
	 * @throws Exception if occurs.
	 */
	public List<Asset> getAll() throws Exception;
	/**Get all asset entities in Database filtered by field and value.
	 * @param field Name of Database column
	 * @param value Value  of Database column
	 * @return The List of Asset entities with this column value defined.
	 * @throws Exception
	 */
	public List<Asset> getAllByFieldValue(String field, String value) throws Exception;
	/**
	 * Insert a new Asset to Database
	 * @param asset to be inserted.
	 * @return the asset inserted successfully.
	 * @throws Exception if occurs
	 */
	public Asset insert(Asset asset) throws Exception;
	/**
	 * Update an existing Asset in database.
	 * @param asset to be updated.
	 * @return the asset updated successfully.
	 * @throws Exception if occurs
	 */
	public Asset update(Asset asset) throws Exception;
}
