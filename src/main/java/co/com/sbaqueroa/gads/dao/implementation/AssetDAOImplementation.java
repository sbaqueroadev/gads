/**
 * 
 */
package co.com.sbaqueroa.gads.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.sbaqueroa.gads.dao.AssetDAO;
import co.com.sbaqueroa.gads.model.implementation.Asset;

/**
 * @author sergio
 * .
 *
 */
@Repository
public class AssetDAOImplementation implements AssetDAO {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.dao.AssetDAO#getAll()
	 */
	@Override
	public List<Asset> getAll() throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Asset> cq = builder.createQuery(Asset.class);
	    Root<Asset> root = cq.from(Asset.class);
	    cq.select(root);
	    return entityManager.createQuery(cq).getResultList();
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.dao.AssetDAO#getAllByFieldValue(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Asset> getAllByFieldValue(String field, String value) throws Exception {
		List<Asset> assets = new ArrayList<Asset>();
		Query query = entityManager.createNativeQuery("SELECT * FROM asset WHERE "+field+" = :value", Asset.class);
		query.setParameter("value", value);
		assets = query.getResultList();
		return assets;
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gads.dao.AssetDAO#insert(co.com.sbaqueroa.gads.model.implementation.Asset)
	 */
	@Override
	@Transactional(readOnly=false)
	public Asset insert(Asset asset) throws Exception {
		entityManager.persist(asset);
		entityManager.flush();
		return asset;
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gads.dao.AssetDAO#update(co.com.sbaqueroa.gads.model.implementation.Asset)
	 */
	@Override
	public Asset update(Asset asset) throws Exception {
		entityManager.refresh(asset);
		entityManager.flush();
		return asset;
	}

}
