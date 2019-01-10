package servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoadMoreServletTest {

	private LoadMoreServlet servlet;  
    private HttpServletRequest mockRequest;  
    private HttpServletResponse mockResponse;  

    @Before  
    public void setUp(){      
        servlet = new LoadMoreServlet();  
          
        //创建request和response的Mock  
        mockRequest = mock(HttpServletRequest.class);  
        mockResponse = mock(HttpServletResponse.class); 
    }  
      

	@Test
	public void testDoPostWithIdIs31() throws FileNotFoundException, IOException, ServletException {
		when(mockResponse.getWriter()).thenReturn(new PrintWriter("index.jsp"));
		when(mockRequest.getParameter("more_pos")).thenReturn("31");
		servlet.doPost(mockRequest, mockResponse);
	}

	@Test
	public void testDoPostWithIdIs0() throws FileNotFoundException, IOException, ServletException {
		when(mockResponse.getWriter()).thenReturn(new PrintWriter("index.jsp"));
		when(mockRequest.getParameter("more_pos")).thenReturn("0");
		servlet.doPost(mockRequest, mockResponse);
	}
	
	@Test
	public void testDoGetWithIdIs31() throws FileNotFoundException, IOException, ServletException {
		when(mockResponse.getWriter()).thenReturn(new PrintWriter("index.jsp"));
		when(mockRequest.getParameter("more_pos")).thenReturn("31");
		servlet.doGet(mockRequest, mockResponse);
	}

	@Test
	public void testDoGetWithIdIs0() throws FileNotFoundException, IOException, ServletException {
		when(mockResponse.getWriter()).thenReturn(new PrintWriter("index.jsp"));
		when(mockRequest.getParameter("more_pos")).thenReturn("0");
		servlet.doGet(mockRequest, mockResponse);
	}
}
