/**
 * 
 */
package co.com.sbaqueroa.gads.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sbaqueroa.gads.dao.AreaDAO;
import co.com.sbaqueroa.gads.model.AreaInterface;
import co.com.sbaqueroa.gads.model.implementation.Area;
import co.com.sbaqueroa.gads.webservices.HomeController;

/**
 * @author sergio
 * Area services.
 *
 */
@Service
public class AreaImpl implements AreaInterface {

	private static final Logger logger = LoggerFactory.getLogger(AreaImpl.class);
	
	/**
	 * Connection with DAO.
	 */
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
			logger.trace(e.getMessage());
			logger.error("Error getting areas caused by: "+ e.getMessage());
			e.printStackTrace();
		}
		return new ArrayList<Area>();
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gads.model.AreaInterface#getById(co.com.sbaqueroa.gads.model.implementation.Area)
	 */
	@Override
	public Area getById(Area area) {
		try {
			return areaDAO.getById(area);
		} catch (Exception e) {
			logger.trace(e.getMessage());
			logger.error("Error getting areas caused by: "+ e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
