/**
 * 
 */
package co.com.sbaqueroa.gdas.dao.implementation;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.sbaqueroa.gads.GdasApplication;
import co.com.sbaqueroa.gads.dao.PersonDAO;
import co.com.sbaqueroa.gads.dao.implementation.PersonDAOImplementetation;

/**
 * @author sergio
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=GdasApplication.class)
@Import(PersonDAOImplementetation.class)
public class PersonDAOImplementationTest {

	@Autowired
	private PersonDAO personDAO;
	

	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		try {
			assertEquals(0,personDAO.getAll().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
