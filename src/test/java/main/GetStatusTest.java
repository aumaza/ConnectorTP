package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;

import ar.com.todopago.api.TodoPagoConector;
import mocks.GetStatusMockParameters;

public class GetStatusTest {

	private Map<String,String> paramsGetStatus=GetStatusMockParameters.getStatusParameters();
	private Map<String,Object> getStatusOKMocked=GetStatusMockParameters.getStatusOKResponse();
	private Map<String,Object> getStatusErrorMocked=GetStatusMockParameters.getStatusErrorResponse();
	
	@Test
	public void GetStatusOKTest(){
		TodoPagoConector tpc = mock(TodoPagoConector.class);
		
		when(tpc.getStatus(paramsGetStatus)).thenReturn(getStatusOKMocked);
			
		assertEquals(true,tpc.getStatus(paramsGetStatus).containsKey("OperationsCollections"));
		
		@SuppressWarnings("unchecked")
		Map<String,Object> oc=(Map<String,Object>)tpc.getStatus(paramsGetStatus).get("OperationsCollections");
		
		assertEquals(true,oc.containsKey("RESULTCODE"));
		assertEquals(true,oc.containsKey("RESULTMESSAGE"));		
		
		assertEquals(-1,oc.get("RESULTCODE"));
		assertEquals("APROBADA",oc.get("RESULTMESSAGE"));
	}
	
	@Test
	public void GetStatusErrorTest(){
		TodoPagoConector tpc = mock(TodoPagoConector.class);
		
		when(tpc.getStatus(paramsGetStatus)).thenReturn(getStatusErrorMocked);
			
		assertNull(tpc.getStatus(paramsGetStatus).get("OperationsCollections"));
	}
}
