package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.Test;

import ar.com.todopago.api.TodoPagoConector;
import mocks.ReturnRequestMockParameters;

public class ReturnRequestTest {

	Map<String, String> RRParameters=ReturnRequestMockParameters.getRRParameters();
	Map<String, Object> RROKResponse=ReturnRequestMockParameters.getRROKResponse();
	Map<String, Object> RRErrorResponse=ReturnRequestMockParameters.getRRErrorResponse();
	Map<String, Object> RRError702Response=ReturnRequestMockParameters.getRRError702Response();
	
	@Test
	public void returnRequesOKTest(){
		TodoPagoConector tpc=mock(TodoPagoConector.class);
		
		when(tpc.returnRequest(RRParameters)).thenReturn(RROKResponse);
		
		Map<String,Object> RRResponse=tpc.returnRequest(RRParameters);
		
		assertEquals(true,RRResponse.containsKey("StatusCode"));
		assertEquals(true,RRResponse.containsKey("StatusMessage"));
		
		assertEquals(2011,RRResponse.get("StatusCode"));
		assertEquals("Operación realizada correctamente",RRResponse.get("StatusMessage"));
	}
	
	@Test
	public void returnRequesErrorTest(){
		TodoPagoConector tpc=mock(TodoPagoConector.class);
		
		when(tpc.returnRequest(RRParameters)).thenReturn(RRErrorResponse);
		
		Map<String,Object> RRResponse=tpc.returnRequest(RRParameters);

		assertEquals(true,RRResponse.containsKey("StatusCode"));
		assertEquals(true,RRResponse.containsKey("StatusMessage"));
		
		assertNotEquals(2011,RRResponse.get("StatusCode"));
		assertEquals(2013,RRResponse.get("StatusCode"));
		assertEquals("No es posible obtener los importes de las comisiones para realizar la devolucion",RRResponse.get("StatusMessage"));
	}
	
	@Test
	public void returnReques702Test(){
		TodoPagoConector tpc=mock(TodoPagoConector.class);
		
		when(tpc.returnRequest(RRParameters)).thenReturn(RRError702Response);
		
		Map<String,Object> RRResponse=tpc.returnRequest(RRParameters);

		
		assertEquals(true,RRResponse.containsKey("StatusCode"));
		assertEquals(true,RRResponse.containsKey("StatusMessage"));
		
		assertNotEquals(2011,RRResponse.get("StatusCode"));
		assertEquals(702,RRResponse.get("StatusCode"));
		assertEquals("Cuenta de Vendedor Invalida",RRResponse.get("StatusMessage"));
	}

}