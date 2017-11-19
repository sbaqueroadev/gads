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

import co.com.sbaqueroa.gads.dao.PersonDAO;
import co.com.sbaqueroa.gads.model.implementation.Person;

/**
 * @author sergio
 * Person DAO implementation.
 */
@Repository
public class PersonDAOImplementetation implements PersonDAO {

	/**
	 * Hibernate's entity manager.
	 */
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

	/* (non-Javadoc)
	 * @see co.com.sbaqueroa.gads.dao.PersonDAO#getById(co.com.sbaqueroa.gads.model.implementation.Person)
	 */
	@Override
	@Transactional
	public Person getById(Person person) throws Exception {
		person = entityManager.find(Person.class, person.getId());
		return person;
	}

}
