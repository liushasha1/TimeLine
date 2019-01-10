package util;

import java.sql.SQLException;
import java.sql.Connection;

/**
 * @author Shashaliu
 * @date 2018/12/23
 */
public class DbUtil {
	private Connection conn = null;
	private String user= "root";
	private String password = "12345678";
	private String url = "jdbc:mysql://127.0.0.1:3306/mydatabase?useUnicode=true&characterEncoding=utf-8&useSSL=false&&allowPublicKeyRetrieval=true";
	
	public DbUtil()
	  {
		bulidConnection();
	  }

	  private void bulidConnection()
	  {
	    try
	    {
	       Class.forName("com.mysql.jdbc.Driver");
	       java.sql.DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	       conn=java.sql.DriverManager.getConnection(getEncryptedUrl(),getEncryptedUser(),getEncryptedPass());
	    }
	    
	    catch (Exception ex) {
	      System.out.println(ex.toString());
	   
	    }
	  }

	  public Connection getConnection()
	  {
	    return conn;
	  }

	  public void close()
	  {
	    try
	    {
	      this.conn.close();
	      this.conn = null;
	    }
	    catch (SQLException ex)
	    {
	    System.out.println(ex.toString());
	    }
	  }
	  
	  private String getEncryptedUrl()
	  {
		  return url;
	  }
	  private String getEncryptedUser()
	  {
		  return user;
	  }
	  
	  private String getEncryptedPass()
	  {
		  return password;
	  }
}
