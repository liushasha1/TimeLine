package model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import model.User;

public class UserTest {
    private User user; 

	@Test
	public void testUserWithNoParameter() {
		user = new User();
		assertEquals(null,user.getTeleNum());
		assertEquals(null,user.getGender());
		assertEquals(null,user.getName());
		assertEquals(null,user.getPassword());
	}
	
	@Test
	public void testUserWithTwoParameters() {
		user = new User("13979001234","1");
		assertEquals("13979001234",user.getTeleNum());
		assertEquals("1",user.getPassword());
	}
	@Test
	public void testUserWithAllParameters() {
		user = new User("13979001234","male","John","1");
		assertEquals("13979001234",user.getTeleNum());
		assertEquals("male",user.getGender());
		assertEquals("John",user.getName());
		assertEquals("1",user.getPassword());
	}
	
	@Test
	public void testSetTeleNum() {
		user = new User();
		user.setTeleNum("13979001234");
		assertEquals("13979001234",user.getTeleNum());
	}
	@Test
	public void testSetGender() {
		user = new User();
		user.setGender("male");
		assertEquals("male",user.getGender());
	}
	@Test
	public void testSetName() {
		user = new User();
		user.setName("John");
		assertEquals("John",user.getName());
	}
	@Test
	public void testSetPassword() {
		user = new User();
		user.setPassword("1");
		assertEquals("1",user.getPassword());
	}
}
