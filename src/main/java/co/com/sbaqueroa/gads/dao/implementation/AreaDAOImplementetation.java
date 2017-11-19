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
import org.springframework.transaction.annotation.Transactional;

import co.com.sbaqueroa.gads.dao.AreaDAO;
import co.com.sbaqueroa.gads.model.implementation.Area;

/**
 * @author sergio
 * Area DAO implementation.
 *
 */
@Repository
public class AreaDAOImplementetation implements AreaDAO {

	/**
	 * Hibernate's entity manager.
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.dao.AreaDAO#getAll()
	 */
	@Override
	public List<Area> getAll() throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Area> cq = builder.createQuery(Area.class);
	    Root<Area> root = cq.from(Area.class);
	    cq.select(root);
	    return entityManager.createQuery(cq).getResultList();
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gads.dao.AreaDAO#getById(co.com.sbaqueroa.gads.model.implementation.Area)
	 */
	@Override
	@Transactional
	public Area getById(Area area) throws Exception {
		area = entityManager.find(Area.class,area.getId());
		return area;
	}

}
