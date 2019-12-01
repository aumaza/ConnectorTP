package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import java.util.Map;

import ar.com.todopago.api.TodoPagoConector;
import mocks.FraudControlMockParameters;
import mocks.SARMockParameters;

public class SendAuthorizeRequestTest {

	private Map<String,String> paramsSAR=SARMockParameters.getSARParameters();
	private Map<String,String> cfParams=FraudControlMockParameters.getFraudControlParameters("Villa General Belgrano","argentina","cordoba","Av. Julio Argentino Roca 81","1010","San Nicolas","Argentina","Buenos Aires","maipu 458","1010");
	private Map<String,Object> rsOKMocked=SARMockParameters.getResponseSarOK();
	private Map<String,Object> rsErrorMocked=SARMockParameters.getResponseSarError();
	private Map<String,Object> rsError702Mocked=SARMockParameters.getResponseSar702Error();
	
	@Test
	public void sendAuthorizeRequestOkTest(){
		
		TodoPagoConector tpc = mock(TodoPagoConector.class);
		
		when(tpc.sendAuthorizeRequest(paramsSAR,cfParams)).thenReturn(rsOKMocked);
		
		Map<String,Object> SARResponse=tpc.sendAuthorizeRequest(paramsSAR,cfParams);
		
		assertEquals(true,SARResponse.containsKey("StatusCode"));
		assertEquals(true,SARResponse.containsKey("StatusMessage"));
		
		assertEquals(-1,SARResponse.get("StatusCode"));
		assertEquals("Solicitud de Autorizacion Registrada",SARResponse.get("StatusMessage"));
	}
	
	@Test
	public void sendAuthorizeRequestErrorTest(){
		
		TodoPagoConector tpc = mock(TodoPagoConector.class);
		
		when(tpc.sendAuthorizeRequest(paramsSAR,cfParams)).thenReturn(rsErrorMocked);
		
		Map<String,Object> SARResponse=tpc.sendAuthorizeRequest(paramsSAR,cfParams);
		
		assertEquals(true,SARResponse.containsKey("StatusCode"));
		assertEquals(true,SARResponse.containsKey("StatusMessage"));
		
		assertNotEquals(-1,SARResponse.get("StatusCode"));
		assertEquals("El campo CSBTCITY es obligatorio. (Min Length 2)",SARResponse.get("StatusMessage"));
	}
	
	@Test
	public void sendAuthorizeRequest702Test(){
		
		TodoPagoConector tpc = mock(TodoPagoConector.class);

		when(tpc.sendAuthorizeRequest(paramsSAR,cfParams)).thenReturn(rsError702Mocked);

		Map<String,Object> SARResponse=tpc.sendAuthorizeRequest(paramsSAR,cfParams);

		assertEquals(true,SARResponse.containsKey("StatusCode"));
		assertEquals(true,SARResponse.containsKey("StatusMessage"));

		assertEquals(702,SARResponse.get("StatusCode"));
		assertNotEquals(-1,SARResponse.get("StatusCode"));
		assertEquals("StatusMessage", "ERROR: Cuenta Inexistente",SARResponse.get("StatusMessage"));
	}

}