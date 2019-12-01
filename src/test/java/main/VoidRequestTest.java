package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.Test;

import ar.com.todopago.api.TodoPagoConector;
import mocks.VoidRequestMockParameters;

public class VoidRequestTest {

	Map<String, String> VRParameters=VoidRequestMockParameters.getVRParameters();
	Map<String, Object> VROKResponse=VoidRequestMockParameters.getVROKResponse();
	Map<String, Object> VRErrorResponse=VoidRequestMockParameters.getVRErrorResponse();
	Map<String, Object> VRError702Response=VoidRequestMockParameters.getVRError702Response();
	
	@Test
	public void voidRequestOKtTest(){
		TodoPagoConector tpc=mock(TodoPagoConector.class);
		
		when(tpc.voidRequest(VRParameters)).thenReturn(VROKResponse);
		
		Map<String,Object> VRResponse=tpc.voidRequest(VRParameters);
		
		assertEquals(true,VRResponse.containsKey("StatusCode"));
		assertEquals(true,VRResponse.containsKey("StatusMessage"));
		
		assertEquals(2011,VRResponse.get("StatusCode"));
		assertEquals("Operación realizada correctamente",VRResponse.get("StatusMessage"));
		
	}
	
	@Test
	public void voidRequestErrortTest(){
		TodoPagoConector tpc=mock(TodoPagoConector.class);
		
		when(tpc.voidRequest(VRParameters)).thenReturn(VRErrorResponse);
		
		Map<String,Object> VRResponse=tpc.voidRequest(VRParameters);

		
		assertEquals(true,VRResponse.containsKey("StatusCode"));
		assertEquals(true,VRResponse.containsKey("StatusMessage"));
		
		assertNotEquals(2011,VRResponse.get("StatusCode"));
		assertEquals(2013,VRResponse.get("StatusCode"));
		assertEquals("No es posible obtener los importes de las comisiones para realizar la devolucion",VRResponse.get("StatusMessage"));
	}
	
	@Test
	public void voidRequest702tTest(){
		TodoPagoConector tpc=mock(TodoPagoConector.class);
		
		when(tpc.voidRequest(VRParameters)).thenReturn(VRError702Response);
		
		Map<String,Object> VRResponse=tpc.voidRequest(VRParameters);

		
		assertEquals(true,VRResponse.containsKey("StatusCode"));
		assertEquals(true,VRResponse.containsKey("StatusMessage"));
		
		assertNotEquals(2011,VRResponse.get("StatusCode"));
		assertEquals(702,VRResponse.get("StatusCode"));
		assertEquals("Cuenta de Vendedor Invalida",VRResponse.get("StatusMessage"));
	}

}