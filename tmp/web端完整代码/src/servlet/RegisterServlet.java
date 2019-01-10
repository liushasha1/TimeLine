package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import model.User;
import dao.DAO;
import util.DbUtil;

/**
 * Servlet implementation class RegisterServlet
 */
/**
 * @author Shashaliu
 * @date 2018/12/23
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	 /**
	  * @see HttpServlet#HttpServlet()
	  */
	 public RegisterServlet() {
	  super();
	 }

	 /**
	  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	  *      response)
	  */
	 @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
	  doPost(request, response);
	 }

	 /**
	  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	  */
	 @Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		     request.setCharacterEncoding("utf-8");
		  
	         String teleNum = request.getParameter("teleNum");  
	         String password1 = request.getParameter("password1");  
	         String password2 = request.getParameter("password2");  
	         String name = request.getParameter("name"); 
	         String gender=request.getParameter("gender");
	         
	        
	         if(gender==null){  
	             response.sendRedirect("register.jsp?message="+"Please input your gender.");  
	             return;  
	         }
	         if(!password1.equals(password2)){  
	             response.sendRedirect("register.jsp?message="+"Password input error.");  
	             return;  
	         }  
	         User user=new User(teleNum,gender,name,password1);
	         DAO dao=new DAO();
	         try {
	         if(dao.login(user).getTeleNum()!=null)
	         {
	           response.sendRedirect("register.jsp?message="+"The user already exists.");  
	         }
	         else {
	         dao.register(user);
	         response.sendRedirect("register.jsp?message="+"Registered successfully.");
	         }
	         }catch (Exception e) {
	                e.printStackTrace();}
	 }

	
	}