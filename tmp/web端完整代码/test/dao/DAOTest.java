package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import model.User;

public class DAOTest {

	private DAO dao;
	private User user = null;
	@Before
	public void initialize() {
		dao = new DAO();
	}
	@Test
	public void testLoginWithLegalUser() throws Exception {
		user = new User("13979001342","1");
		assertEquals(user.getTeleNum(),dao.login(user).getTeleNum());
		assertEquals(user.getPassword(),dao.login(user).getPassword());
	}

	@Test
	public void testLoginWithIllegalUser() throws Exception {
		user = new User("13979001999","1");
		assertEquals(null,dao.login(user).getTeleNum());
		assertEquals(null,dao.login(user).getPassword());
	}
	
	@Test
	public void testLoginWithNull() throws Exception {
		assertEquals(null,dao.login(user).getTeleNum());
		assertEquals(null,dao.login(user).getPassword());
	}
	
	@Test
	public void testRegisterWithLegalUser() throws Exception {
		user = new User("13979011347","女","John","1");
		assertEquals(true,dao.register(user));
	}
	
	@Test
	public void testRegisterWithIllegalUser() throws Exception {
		user = new User("13979001342","女","John","1");
		assertEquals(false,dao.register(user));
	}
	
	@Test
	public void testRegisterWithNull() throws Exception {
		assertEquals(false,dao.register(user));
	}
	
	
}
