/**
 * 
 */
package co.com.sbaqueroa.gdas.dao.implementation;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.sbaqueroa.gads.GdasApplication;
import co.com.sbaqueroa.gads.dao.AreaDAO;
import co.com.sbaqueroa.gads.dao.AssetDAO;
import co.com.sbaqueroa.gads.dao.CityDAO;
import co.com.sbaqueroa.gads.dao.PersonDAO;
import co.com.sbaqueroa.gads.dao.implementation.AreaDAOImplementetation;
import co.com.sbaqueroa.gads.dao.implementation.AssetDAOImplementation;
import co.com.sbaqueroa.gads.model.implementation.Area;
import co.com.sbaqueroa.gads.model.implementation.Asset;
import co.com.sbaqueroa.gads.model.implementation.AssignedAsset;
import co.com.sbaqueroa.gads.model.implementation.City;
import co.com.sbaqueroa.gads.model.implementation.Person;
/**
 * @author sergio
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=GdasApplication.class)
@Import(AreaDAOImplementetation.class)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class DAOImplementationIT {

	@Autowired
	private AssetDAO assetDAO;
	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private AreaDAO areaDAO;
	@Autowired
	private CityDAO cityDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Asset auxAsset;
	private Person auxPerson;
	private City auxCity;
	private Area auxArea;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testAll() {
		try {
			auxAsset = new Asset();
			Date d = new Date();
			long dt = ((long)86400000*100);
			d.setTime(new Date().getTime()-dt);
			auxAsset.setBuyDate(d);
			auxAsset.setBuyPrice(1000);
			auxAsset.setColor("red");
			auxAsset.setInventoryNumber("123");
			auxAsset.setName("name");
			auxAsset.setSerial("123ab");
			auxAsset.setStatus("active");
			auxAsset.setType("a");
			
			auxPerson = new Person();
			auxPerson.setName("sergio");
			
			auxCity = new City();
			auxCity.setName("city name");
			
			auxArea = new Area();
			auxArea.setName("name area");
			auxArea.setCity(auxCity);
			
			AssignedAsset assignedAsset = new AssignedAsset();
			assignedAsset.setPerson(auxPerson);
			assignedAsset.setAsset(auxAsset);
			
			auxPerson.getAssignedAssets().add(assignedAsset);
			auxAsset.setAssignedAsset(assignedAsset);
			assertEquals(auxAsset, assetDAO.insert(auxAsset));
			assertEquals(auxAsset.getAssignedAsset().getPerson().getName()
					, assetDAO.getAllByFieldValue(Asset.SERIAL_FIELD, "123ab")
					.get(0).getAssignedAsset().getPerson().getName());
			
			assignedAsset = new AssignedAsset();
			assignedAsset.setArea(auxArea);
			assignedAsset.setAsset(auxAsset);
			
			auxArea.getAssignedAssets().add(assignedAsset);
			auxAsset.setAssignedAsset(assignedAsset);
			assertEquals(auxAsset, assetDAO.insert(auxAsset));
			assertEquals(auxAsset.getAssignedAsset().getArea().getCity().getName()
					, assetDAO.getAllByFieldValue(Asset.SERIAL_FIELD, "123ab")
					.get(0).getAssignedAsset().getArea().getCity().getName());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown");
		}
	}

}
