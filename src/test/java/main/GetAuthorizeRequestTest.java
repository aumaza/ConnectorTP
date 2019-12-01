package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import java.util.Map;

import ar.com.todopago.api.TodoPagoConector;
import mocks.GAAMockParameters;


public class GetAuthorizeRequestTest {

	private Map<String,String> paramsGAA=GAAMockParameters.getAAParameters();
	private Map<String,Object> rGAAOKMocked=GAAMockParameters.getAuthorizeAnswerOKResponse();
	private Map<String,Object> rGAAErrorMocked=GAAMockParameters.getAuthorizeAnswerErrorResponse();
	private Map<String,Object> rGAAError702Mocked=GAAMockParameters.getAuthorizeAnswerError702Response();
	
	@Test
	public void GetAuthorizeRequestOkTest(){
		
		TodoPagoConector tpc = mock(TodoPagoConector.class);
		
		when(tpc.getAuthorizeAnswer(paramsGAA)).thenReturn(rGAAOKMocked);
		
		Map<String,Object> GAAResponse=tpc.getAuthorizeAnswer(paramsGAA);
		
		assertEquals(true,GAAResponse.containsKey("StatusCode"));
		assertEquals(true,GAAResponse.containsKey("StatusMessage"));
		assertEquals(true,GAAResponse.containsKey("AuthorizationKey"));
		assertEquals(true,GAAResponse.containsKey("EncodingMethod"));
		assertEquals(true,GAAResponse.containsKey("Payload"));
				
		assertEquals(-1,GAAResponse.get("StatusCode"));
		assertEquals("APROBADA",GAAResponse.get("StatusMessage"));
		
	}
	
	@Test
	public void GetAuthorizeRequestErrorTest(){
		
		TodoPagoConector tpc = mock(TodoPagoConector.class);
		
		when(tpc.getAuthorizeAnswer(paramsGAA)).thenReturn(rGAAErrorMocked);
		
		Map<String,Object> GAAResponse=tpc.getAuthorizeAnswer(paramsGAA);
		
		assertEquals(true,GAAResponse.containsKey("StatusCode"));
		assertEquals(true,GAAResponse.containsKey("StatusMessage"));
		
		assertNotEquals(-1,GAAResponse.get("StatusCode"));
		assertEquals("ERROR: Transaccion Enexistente",GAAResponse.get("StatusMessage"));
	}
	
	@Test
	public void GetAuthorizeRequest702Test(){
		
		TodoPagoConector tpc = mock(TodoPagoConector.class);
		
		when(tpc.getAuthorizeAnswer(paramsGAA)).thenReturn(rGAAError702Mocked);
		
		Map<String,Object> GAAResponse=tpc.getAuthorizeAnswer(paramsGAA);
		
		assertEquals(true,GAAResponse.containsKey("StatusCode"));
		assertEquals(true,GAAResponse.containsKey("StatusMessage"));
		
		assertNotEquals(-1,GAAResponse.get("StatusCode"));
		assertEquals(702,GAAResponse.get("StatusCode"));
		assertEquals("Cuenta de Vendedor Invalida",GAAResponse.get("StatusMessage"));
	}

}