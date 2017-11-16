/**
 * 
 */
package co.com.sbaqueroa.gads.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sbaqueroa.gads.dao.CityDAO;
import co.com.sbaqueroa.gads.model.CityInterface;
import co.com.sbaqueroa.gads.model.implementation.City;

/**
 * @author sergio
 * Bee Smart S.A.S.
 *
 */
@Service
public class CityImpl implements CityInterface {

	@Autowired
	private CityDAO cityDAO;
	
	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.model.AreaInterface#getAll()
	 */
	@Override
	public List<City> getAll() {
		try {
			return cityDAO.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<City>();
	}

}
