/**
 * 
 */
package co.com.sbaqueroa.gads.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sbaqueroa.gads.dao.AreaDAO;
import co.com.sbaqueroa.gads.model.AreaInterface;
import co.com.sbaqueroa.gads.model.implementation.Area;

/**
 * @author sergio
 * .
 *
 */
@Service
public class AreaImpl implements AreaInterface {

	@Autowired
	private AreaDAO areaDAO;
	
	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.model.AreaInterface#getAll()
	 */
	@Override
	public List<Area> getAll() {
		try {
			return areaDAO.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Area>();
	}

	@Override
	public Area getById(Area area) {
		try {
			return areaDAO.getById(area);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
