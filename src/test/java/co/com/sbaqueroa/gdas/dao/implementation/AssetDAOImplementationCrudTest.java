/**
 * 
 */
package co.com.sbaqueroa.gdas.dao.implementation;

import static org.junit.Assert.assertEquals;

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
import co.com.sbaqueroa.gads.dao.AssetDAO;
import co.com.sbaqueroa.gads.dao.implementation.AreaDAOImplementetation;
import co.com.sbaqueroa.gads.model.implementation.Asset;
import co.com.sbaqueroa.gads.model.implementation.AssignedAsset;
/**
 * @author sergio
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=GdasApplication.class)
@Import(AreaDAOImplementetation.class)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class AssetDAOImplementationCrudTest {

	@Autowired
	private AssetDAO assetDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			entityManager.createNativeQuery("DELETE FROM asset").executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testEmpty() {
		try {
			assertEquals(0,assetDAO.getAll().size());
			Asset aux = new Asset();
			Date d = new Date();
			long dt = ((long)86400000*100);
			d.setTime(new Date().getTime()-dt);
			aux.setBuyDate(d);
			aux.setBuyPrice(1000);
			aux.setColor("red");
			aux.setInventoryNumber("123");
			aux.setName("name");
			aux.setSerial("123ab");
			aux.setStatus("active");
			aux.setType("a");
			assertEquals(aux, assetDAO.insert(aux));
			Asset aux2 = new Asset();
			Date d2 = new Date();
			long dt2 = ((long)86400000*50);
			d.setTime(new Date().getTime()-dt2);
			aux2.setBuyDate(d2);
			aux2.setBuyPrice(1000);
			aux2.setColor("red");
			aux2.setInventoryNumber("123");
			aux2.setName("name");
			aux2.setSerial("123a");
			aux2.setStatus("active");
			aux2.setType("b");
			assertEquals(aux2, assetDAO.insert(aux2));
			assertEquals(aux.getSerial(), assetDAO.getAllByFieldValue("type", "a").get(0).getSerial());
			assertEquals(aux2.getSerial(), assetDAO.getAllByFieldValue("type", "b").get(0).getSerial());
			assertEquals(aux.getType(), assetDAO.getAllByFieldValue("serial", "123ab").get(0).getType());
			assertEquals(aux2.getType(), assetDAO.getAllByFieldValue("serial", "123a").get(0).getType());
			aux2.setName("name updated");
			assertEquals(aux2, assetDAO.update(aux2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
