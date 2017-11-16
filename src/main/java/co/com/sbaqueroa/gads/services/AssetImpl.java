/**
 * 
 */
package co.com.sbaqueroa.gads.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sbaqueroa.gads.dao.AssetDAO;
import co.com.sbaqueroa.gads.model.AssetInterface;
import co.com.sbaqueroa.gads.model.implementation.Area;
import co.com.sbaqueroa.gads.model.implementation.Asset;

/**
 * @author sergio
 * Bee Smart S.A.S.
 *
 */
@Service
public class AssetImpl implements AssetInterface {

	@Autowired
	private AssetDAO assetDAO;
	
	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.model.AreaInterface#getAll()
	 */
	@Override
	public List<Asset> getAll() {
		try {
			return assetDAO.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Asset>();
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.model.AssetInterface#getAllByFieldValue(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Asset> getAllByFieldValue(String field, String value) {
		try {
			return assetDAO.getAllByFieldValue(field, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Asset>();
	}

}
