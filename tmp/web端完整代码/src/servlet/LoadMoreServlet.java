package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import util.DbUtil;

/**
 * Servlet implementation class LoadMoreServlet
 */
@WebServlet("/LoadMoreServlet")
public class LoadMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadMoreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		ResultSet re = null;
		Statement stmt = null;
		Connection con = null;
		DbUtil db = new DbUtil();
		String userName = null, descript = null, time = null, image = null;
		int id = 0;
		int startId = Integer.parseInt(request.getParameter("more_pos"));
		String outstring = "";
		try {
			con = (Connection) db.getConnection();
			stmt = con.createStatement();
			// 连接数据库获取小于id的最新的十条动态
			re = stmt.executeQuery("SELECT * FROM dynamic where ID < " + startId + " order by ID DESC limit 10");
			if (re.next()) {
				if (re.previous()) {
				};
				while (re.next()) {
					descript = re.getString("content");
					userName = re.getString("userName");
					Date time1 = new Date(re.getTimestamp("time").getTime());
					SimpleDateFormat formattime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					time = formattime.format(time1);
					image = re.getString("image");
					id = re.getInt("ID");

					outstring += "<div class=\"row\" name=\"dynamic\">";
					outstring += "<div class=\"col-md-12\">";
					outstring += "<p name=\"id\" style=\"visibility: hidden\" >" + id + "</p>";
					outstring += "<ul class=\"timeline\"><li>";
					outstring += "<span style=\"color: blue\">" + userName + "</span>";
					outstring += "<p style=\"color: blue\" class=\"float-right\" name=\"time\">" + time + "</p>";
					outstring += "<p name=\"hiddenTime\" style=\"visibility: hidden\">" + time + "</p>";
					outstring += "<p>" + descript + "</p>";
					if (image != null && image.equals(""))
						outstring += "<img src=\"" + image + "\" class=\"img-thumbnail\">";
					outstring += "</li> </ul></div></div>";
				}
				out.write(outstring);
			} else {
				out.write("<p style=\"color: red\" class=\"float-right\">已加载所有动态</p>");
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.toString());
		} finally {
			if (re != null) {
				try {
					re.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
