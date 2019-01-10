package servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.websocket.RemoteEndpoint;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DAO;

public class WebSocketTest {
	WebSocket webSocket;
	Session mockSession;
	String message;
	@Before  
    public void setUp(){          
		webSocket=new WebSocket();
		mockSession = spy(Session.class);
		message="hello!";
    }  
	@Test
	public void testOnOpen() {
		int bnum=webSocket.getOnlineCount();
		webSocket.onOpen(mockSession);
		assertEquals(bnum+1,webSocket.getOnlineCount());
	}

	@Test
	public void testOnClose() {
		int bnum=webSocket.getOnlineCount();
		webSocket.onClose();
		assertEquals(bnum-1,webSocket.getOnlineCount());
	}
	
	@Test
	public void testOnMessage() throws Exception {
		webSocket.onMessage(message,mockSession);
	}
	
	@Test
	public void testOnError() throws Exception {
	   RuntimeException runError = new RuntimeException("Surprise!");
	   webSocket.onError(mockSession,runError);
	}
}
