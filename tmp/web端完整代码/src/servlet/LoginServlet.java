package servlet;

import java.io.IOException;

import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import dao.DAO;
import model.User;
import util.DbUtil;

@WebServlet("/loginServlet")
/**
   * @author liushasha
   * @date 2018/12/23
   */
public class LoginServlet extends HttpServlet {
	
		/**
		 * @see HttpServlet#HttpServlet()
		 */
		public LoginServlet() {
			super();
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			  this.doPost(request, response);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			String teleNum = request.getParameter("telephone");
			String password = request.getParameter("password");
			User user = new User(teleNum, password);
			DAO dao = new DAO();
			try {
				User resultUser = dao.login(user);
				if (resultUser.getTeleNum() == null) {
					response.sendRedirect("login.jsp?msg="+"no such user!");
					return;
				}
				else if (!resultUser.getPassword().equals(password)) {
					response.sendRedirect("login.jsp?msg="+"wrong password!");
					return;
				}
				else {
					response.sendRedirect("index.jsp");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			
		}
	}
