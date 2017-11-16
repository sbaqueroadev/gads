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

import co.com.sbaqueroa.gads.dao.PersonDAO;
import co.com.sbaqueroa.gads.model.implementation.Person;

/**
 * @author sergio
 * Bee Smart S.A.S.
 *
 */
@Repository
public class PersonDAOImplementetation implements PersonDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gdas.dao.PersonDAO#getAll()
	 */
	@Override
	public List<Person> getAll() throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Person> cq = builder.createQuery(Person.class);
	    Root<Person> root = cq.from(Person.class);
	    cq.select(root);
	    return entityManager.createQuery(cq).getResultList();
	}

}
