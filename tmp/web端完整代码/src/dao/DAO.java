package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import util.DbUtil;

/**
 * @author Shashaliu
 * @date 2018/12/23
 */
public class DAO {
	
	/**
	 * 
	 * 登录功能，连接数据库判断是否存在该用户
	 * 
	 */
	public User login(User user) throws Exception  {
		User resultUser = new User();
		String sql = "select * from users where userId=?";
		DbUtil db = new DbUtil();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			if(user!=null)
			{
			con = db.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getTeleNum());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				resultUser.setName(rs.getString("userName"));
				resultUser.setTeleNum(rs.getString("userId"));
				resultUser.setPassword(rs.getString("password"));
				resultUser.setGender(rs.getString("gender"));
			}}
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString());
		} finally {
			if (rs != null) {
					rs.close();
			}
			if (pstmt != null) {
					pstmt.close();
				}
			if (con != null) {
					con.close();
				}
			db.close();
		}
		return resultUser;
	}

	/**
	 * 
	 * 注册功能
	 * 
	 */
	public boolean register(User user) throws Exception {
		boolean flag=false;
		DbUtil db = new DbUtil();
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = "INSERT INTO users(userId,userName,password,gender)VALUES(?,?,?,?)";
		try {
			if(user!=null) {
			con = db.getConnection();	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getTeleNum());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getGender());
			pstmt.executeUpdate();
			flag=true;
			}
		} catch (Exception e) {
			System.out.println("SQL Exception: " + e.toString());
		} finally {
			if (pstmt != null) {
					pstmt.close();
			}
			if (con != null) {
					con.close();
			}
			db.close();
		}
		return flag;
	}
}