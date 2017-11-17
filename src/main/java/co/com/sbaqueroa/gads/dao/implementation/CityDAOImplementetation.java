/**
 * 
 */
package co.com.sbaqueroa.gads.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import co.com.sbaqueroa.gads.dao.CityDAO;
import co.com.sbaqueroa.gads.model.implementation.City;

/**
 * @author sergio
 * .
 *
 */
@Repository
public class CityDAOImplementetation implements CityDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.dao.CityDAO#getAll()
	 */
	@Override
	public List<City> getAll() throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<City> cq = builder.createQuery(City.class);
	    Root<City> root = cq.from(City.class);
	    cq.select(root);
	    return entityManager.createQuery(cq).getResultList();
	}

}
