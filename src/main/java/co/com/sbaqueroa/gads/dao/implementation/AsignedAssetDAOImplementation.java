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

import co.com.sbaqueroa.gads.dao.AssignedAssetDAO;
import co.com.sbaqueroa.gads.model.implementation.AssignedAsset;

/**
 * @author sergio
 * Assigned asset DAO implementation.
 *
 */
@Repository
public class AsignedAssetDAOImplementation implements AssignedAssetDAO {

	
	/**
	 * Hibernate's entity manager.
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.dao.AssetDAO#getAll()
	 */
	@Override
	public List<AssignedAsset> getAll() throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<AssignedAsset> cq = builder.createQuery(AssignedAsset.class);
	    Root<AssignedAsset> root = cq.from(AssignedAsset.class);
	    cq.select(root);
	    return entityManager.createQuery(cq).getResultList();
	}

}
