package util;

import static org.junit.Assert.*;
import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class DbUtilTest {

	private DbUtil dbUtil;

	@Test
	public void testDbUtil() {
		dbUtil = new DbUtil();
		assertThat(dbUtil.getConnection(),not(equalTo(null)));
	}

	@Test
	public void testClose() {
		dbUtil = new DbUtil();
		dbUtil.close();
		assertThat(dbUtil.getConnection(),equalTo(null));
	}
	
	
}
