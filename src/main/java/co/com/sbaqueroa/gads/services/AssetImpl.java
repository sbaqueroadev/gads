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
 * .
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

	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gads.model.AssetInterface#add(co.com.sbaqueroa.gads.model.implementation.Asset)
	 */
	@Override
	public boolean add(Asset asset) {
		try {
			assetDAO.insert(asset);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gads.model.AssetInterface#update(co.com.sbaqueroa.gads.model.implementation.Asset)
	 */
	@Override
	public boolean update(Asset asset) {
		try {
			assetDAO.update(asset);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
