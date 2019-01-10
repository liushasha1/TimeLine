package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class DynamicTest {
    private Dynamic dynamic;

	@Test
	public void testDynamicWithNoParameter() {
		dynamic = new Dynamic();
		assertEquals(null,dynamic.getUserName());
		assertEquals(null,dynamic.getContent());
		assertEquals(null,dynamic.getTime());
		assertEquals(null,dynamic.getImage());
		assertEquals(0,dynamic.getId());
	}
	
	@Test
	public void testDynamicWithParameter() {
		dynamic = new Dynamic("John","可爱如我","image/1.jpg","2018-12-24 11:30:00",29);
		assertEquals("John",dynamic.getUserName());
		assertEquals("可爱如我",dynamic.getContent());
		assertEquals("image/1.jpg",dynamic.getImage());
		assertEquals("2018-12-24 11:30:00",dynamic.getTime());
		assertEquals(29,dynamic.getId());
	}
	
	@Test
	public void testSetUserName() {
		dynamic = new Dynamic();
		dynamic.setUserName("John");
		assertEquals("John",dynamic.getUserName());
	}
	@Test
	public void testSetContent() {
		dynamic = new Dynamic();
		dynamic.setContent("可爱如我");
		assertEquals("可爱如我",dynamic.getContent());
	}
	@Test
	public void testSetImage() {
		dynamic = new Dynamic();
		dynamic.setImage("image/1.jpg");
		assertEquals("image/1.jpg",dynamic.getImage());
	}
	@Test
	public void testSetTime() {
		dynamic = new Dynamic();
		dynamic.setTime("2018-12-24 11:30:00");
		assertEquals("2018-12-24 11:30:00",dynamic.getTime());
	}
	
	@Test
	public void testSetId() {
		dynamic = new Dynamic();
		dynamic.setId(20);
		assertEquals(20,dynamic.getId());
	}
}
