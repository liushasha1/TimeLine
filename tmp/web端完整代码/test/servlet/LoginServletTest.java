package servlet;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class LoginServletTest {

	private LoginServlet servlet;  
    private HttpServletRequest mockRequest;  
    private HttpServletResponse mockResponse;  
    @Before  
    public void setUp(){  
          
        servlet = new LoginServlet();  
          
        //创建request和response的Mock  
        mockRequest = mock(HttpServletRequest.class);  
        mockResponse = mock(HttpServletResponse.class);   
    }  
      
    @After  
    public void tearDown(){  
    }  
  
    /** 
     * 测试doPost方法 
     * @throws IOException 
     * @throws ServletException 
     * */  
    @Test  
    public void testDoPostWithNoSuchUser() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("telephone")).thenReturn(null);
        //开始测试Servlet的doPost方法  
        servlet.doPost(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("login.jsp?msg="+"no such user!");
    }  
    
    @Test  
    public void testDoPostWithWrongPasswordUser() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("telephone")).thenReturn("13979001342");
    	when(mockRequest.getParameter("password")).thenReturn("2");
        //开始测试Servlet的doPost方法  
        servlet.doPost(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("login.jsp?msg="+"wrong password!");
    }  
    
    @Test  
    public void testDoPostWithCorrectUser() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("telephone")).thenReturn("13979001342");
    	when(mockRequest.getParameter("password")).thenReturn("1");
        //开始测试Servlet的doPost方法  
        servlet.doPost(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("index.jsp");
    } 
    @Test  
    public void testDoGetWithNoSuchUser() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("telephone")).thenReturn(null);
        //开始测试Servlet的doGet方法  
        servlet.doGet(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("login.jsp?msg="+"no such user!");
    }  
    
    @Test  
    public void testDoGetWithWrongPasswordUser() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("telephone")).thenReturn("13979001342");
    	when(mockRequest.getParameter("password")).thenReturn("2");
        //开始测试Servlet的doGet方法  
        servlet.doGet(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("login.jsp?msg="+"wrong password!");
    }  
    
    @Test  
    public void testDoGetWithCorrectUser() throws IOException, ServletException{  
          
    	when(mockRequest.getParameter("telephone")).thenReturn("13979001342");
    	when(mockRequest.getParameter("password")).thenReturn("1");
        //开始测试Servlet的doGet方法  
        servlet.doGet(mockRequest, mockResponse);  
        
        verify(mockResponse).sendRedirect("index.jsp");
    } 
    
   
}
