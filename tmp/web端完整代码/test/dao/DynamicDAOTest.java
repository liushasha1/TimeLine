package dao;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import java.sql.SQLException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import model.Dynamic;

public class DynamicDAOTest {
	private  DynamicDAO  dynamicDAO;
	private String time = null;
	
	@Before
	public void initialize() {
		dynamicDAO = new DynamicDAO();
	}
	
	@Test
	public void testGetDynamicList() throws Exception {
		List<Dynamic> dynamics = dynamicDAO.getDynamicList();
		assertEquals(10,dynamics.size());
	}
	
	@Test
	public void testGetDynamicNumberByLegalTime() throws Exception {
		String time = "2018-10-30 10:00:00";
		assertThat(dynamicDAO.getDynamicNumber(time),not(equalTo(0)));
	}
	
	@Test
	public void testGetDynamicNumberByNull() throws Exception {
		assertThat(dynamicDAO.getDynamicNumber(null),equalTo(0));
	}
	
	
}
