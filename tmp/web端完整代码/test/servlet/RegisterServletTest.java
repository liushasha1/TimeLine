package servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DAO;
import model.User;

public class RegisterServletTest {

	private RegisterServlet servlet;  
    private HttpServletRequest mockRequest;  
    private HttpServletResponse mockResponse;  
    
    @Before  
    public void setUp(){  
          
        servlet = new RegisterServlet();  
          
        //创建request和response的Mock  
        mockRequest = mock(HttpServletRequest.class);  
        mockResponse = mock(HttpServletResponse.class); 
    }  
    
	@After
	public void tearDown() throws Exception {
	}

	/** 
     * 测试doGet方法 
     * @throws IOException 
     * @throws ServletException 
     * */  
    @Test  
    public void testDoGetWithNullGender() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("gender")).thenReturn(null);
        //开始测试Servlet的doGet方法  
        servlet.doGet(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("register.jsp?message="+"Please input your gender.");
    
    }  
    
    @Test  
    public void testDoGetWithWrongPasswordInput() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("teleNum")).thenReturn("13779001342");
    	when(mockRequest.getParameter("name")).thenReturn("John");
    	when(mockRequest.getParameter("gender")).thenReturn("male");
    	when(mockRequest.getParameter("password1")).thenReturn("1");
    	when(mockRequest.getParameter("password2")).thenReturn("2");
        //开始测试Servlet的doGet方法  
        servlet.doGet(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("register.jsp?message="+"Password input error.");
    }  
    
    @Test  
    public void testDoGetWithExistentUser() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("teleNum")).thenReturn("13979001342");
    	when(mockRequest.getParameter("name")).thenReturn("John");
    	when(mockRequest.getParameter("gender")).thenReturn("male");
    	when(mockRequest.getParameter("password1")).thenReturn("1");
    	when(mockRequest.getParameter("password2")).thenReturn("1");
        //开始测试Servlet的doGet方法  
        servlet.doGet(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("register.jsp?message="+"The user already exists."); 
    } 
    @Test  
    public void testDoGetWithLegalUser() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("teleNum")).thenReturn("13979101342");
    	when(mockRequest.getParameter("name")).thenReturn("John");
    	when(mockRequest.getParameter("gender")).thenReturn("male");
    	when(mockRequest.getParameter("password1")).thenReturn("1");
    	when(mockRequest.getParameter("password2")).thenReturn("1");
        //开始测试Servlet的doGet方法  
        servlet.doGet(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("register.jsp?message="+"Registered successfully.");
    }  
    
    /** 
     * 测试doPost方法 
     * @throws IOException 
     * @throws ServletException 
     * */  
    @Test  
    public void testDoPostWithNullGender() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("gender")).thenReturn(null);
        //开始测试Servlet的doPost方法  
        servlet.doPost(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("register.jsp?message="+"Please input your gender.");
    }  
    
    @Test  
    public void testDoPostWithWrongPasswordInput() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("teleNum")).thenReturn("13779001342");
    	when(mockRequest.getParameter("name")).thenReturn("John");
    	when(mockRequest.getParameter("gender")).thenReturn("male");
    	when(mockRequest.getParameter("password1")).thenReturn("1");
    	when(mockRequest.getParameter("password2")).thenReturn("2");

        //开始测试Servlet的doPost方法  
        servlet.doPost(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("register.jsp?message="+"Password input error.");
    }  
    
    @Test  
    public void testDoPostWithExistentUser() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("teleNum")).thenReturn("13979001342");
    	when(mockRequest.getParameter("name")).thenReturn("John");
    	when(mockRequest.getParameter("gender")).thenReturn("male");
    	when(mockRequest.getParameter("password1")).thenReturn("1");
    	when(mockRequest.getParameter("password2")).thenReturn("1");
    	
        //开始测试Servlet的doPost方法  
        servlet.doPost(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("register.jsp?message="+"The user already exists."); 
    } 
    @Test  
    public void testDoPostWithLegalUser() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("teleNum")).thenReturn("13879701342");
    	when(mockRequest.getParameter("name")).thenReturn("Tom");
    	when(mockRequest.getParameter("gender")).thenReturn("male");
    	when(mockRequest.getParameter("password1")).thenReturn("1");
    	when(mockRequest.getParameter("password2")).thenReturn("1");
        //开始测试Servlet的doPost方法  
        servlet.doPost(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("register.jsp?message="+"Registered successfully.");
    }  
    
   
}
