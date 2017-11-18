/**
 * 
 */
package co.com.sbaqueroa.gdas.dao.implementation;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.com.sbaqueroa.gads.GdasApplication;
import co.com.sbaqueroa.gads.dao.AreaDAO;
import co.com.sbaqueroa.gads.dao.implementation.AreaDAOImplementetation;

/**
 * @author sergio
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=GdasApplication.class)
@Import(AreaDAOImplementetation.class)
public class AreaDAOImplementationTest {

	@Autowired
	private AreaDAO areaDAO;
	

	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		try {
			assertEquals(0,areaDAO.getAll().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
