/**
 * 
 */
package co.com.sbaqueroa.gads.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sbaqueroa.gads.dao.AssignedAssetDAO;
import co.com.sbaqueroa.gads.model.AssignedAssetInterface;
import co.com.sbaqueroa.gads.model.implementation.AssignedAsset;

/**
 * @author sergio
 * Assigned Asset services.
 *
 */
@Service
public class AssignedAssetImpl implements AssignedAssetInterface {

	/**
	 * Connection with DAO.
	 */
	@Autowired
	private AssignedAssetDAO assignedAssetDAO;
	
	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.model.AreaInterface#getAll()
	 */
	@Override
	public List<AssignedAsset> getAll() {
		try {
			return assignedAssetDAO.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<AssignedAsset>();
	}


}
