package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Dynamic;
import util.DbUtil;

/**
 * @author Shashaliu
 * @date 2018/12/23
 */
public class DynamicDAO {

	/**
	 * 
	 * 连接数据库获取最新的十条动态
	 * 
	 */
	public List<Dynamic> getDynamicList() throws Exception {
		List<Dynamic> dynamics = new ArrayList<>();
		DbUtil db = new DbUtil();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = db.getConnection();
			String sql = "SELECT * FROM dynamic where time < now() order by ID desc limit 10";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Date time1 = new Date(rs.getTimestamp("time").getTime());
				SimpleDateFormat formattime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String pubtime = formattime.format(time1);
				Dynamic tmp = new Dynamic(rs.getString("userName"), rs.getString("content"), rs.getString("image"),
						pubtime, rs.getInt("ID"));
				dynamics.add(tmp);
			}
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
		
		return dynamics;
	}

	/**
	 * 
	 * 连接数据库获取比输入时间time之后的动态的总条数
	 * 
	 */
	public int getDynamicNumber(String time) throws Exception {
		int num = 0;
		DbUtil db = new DbUtil();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if(time!=null) {
			con = db.getConnection();
			String sql = "SELECT * FROM dynamic where time > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, time);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				num++;
			}
			}
		} catch (Exception e) {
			System.out.println("SQL Exception: " + e.toString());
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
		return num;
	}
}
