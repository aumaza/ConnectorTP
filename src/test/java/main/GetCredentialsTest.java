package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.MalformedURLException;

import org.junit.Test;

import ar.com.todopago.api.TodoPagoConector;
import ar.com.todopago.api.exceptions.ConnectionException;
import ar.com.todopago.api.exceptions.EmptyFieldException;
import ar.com.todopago.api.exceptions.EmptyFieldUserException;
import ar.com.todopago.api.exceptions.ResponseException;
import ar.com.todopago.api.model.User;
import mocks.GetCredentiasMockParameters;

public class GetCredentialsTest {

	User mockUser=GetCredentiasMockParameters.getUser();
	
	@Test(expected=EmptyFieldException.class)
	public void nullUserTest() throws EmptyFieldException, ResponseException, ConnectionException, MalformedURLException{
		User user=getInstanceUser();
		TodoPagoConector tpc=getInstanceConector();
		
		user.setUser(null);

		assertNull(user.getUser());
		tpc.getCredentials(user);
	}	
	
	
	@Test(expected=EmptyFieldUserException.class)
	public void emptyUserTest() throws EmptyFieldException, ResponseException, ConnectionException, MalformedURLException{
		User user=getInstanceUser();
		TodoPagoConector tpc=getInstanceConector();
		
		user.setUser("");

		assertEquals(true,user.getUser().isEmpty());
		tpc.getCredentials(user);
	
	}	
	
	
	@Test(expected=EmptyFieldUserException.class)
	public void nullPasswordTest() throws EmptyFieldException, ResponseException, ConnectionException, MalformedURLException{
		User user=getInstanceUser();
		TodoPagoConector tpc=getInstanceConector();
		
		user.setPassword(null);

		assertNull(user.getPassword());
		tpc.getCredentials(user);
	}
	
	@Test(expected=EmptyFieldUserException.class)
	public void emptyPasswordTest() throws EmptyFieldException, ResponseException, ConnectionException, MalformedURLException{
		User user=getInstanceUser();
		TodoPagoConector tpc=getInstanceConector();
		
		user.setPassword("");

		assertEquals(true,user.getPassword().equals(""));
		tpc.getCredentials(user);
	}
	
	@Test()
	public void getCredentialsOKTest() throws EmptyFieldException, ResponseException, ConnectionException, MalformedURLException{
		TodoPagoConector tpc=mock(TodoPagoConector.class);
		User user=mock(User.class);
		
		when(user.getUser()).thenReturn("user");
		when(user.getPassword()).thenReturn("Password");
		when(tpc.getCredentials(getInstanceUser())).thenReturn(mockUser);

		assertEquals("test@test.com.ar",mockUser.getUser());
		assertEquals("test1234",mockUser.getPassword());
	}
	
	/*---- Auxiliary methods ----*/
	private User getInstanceUser(){
		return new User();
	}
	
	private TodoPagoConector getInstanceConector() throws MalformedURLException{
		return new TodoPagoConector(111);
	}
}