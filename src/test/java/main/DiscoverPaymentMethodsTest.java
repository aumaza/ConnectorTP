package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ar.com.todopago.api.TodoPagoConector;
import mocks.PaymentsMethodsMockParameters;

public class DiscoverPaymentMethodsTest{

	//Map<String,String> DPMParameters=PaymentsMethodsMockParameters.getPMParameters();
	
	Map<String,Object> DPMOKResponse=PaymentsMethodsMockParameters.paymentMethodsOKResponse(true);
	Map<String,Object> DPMErrorResponse=PaymentsMethodsMockParameters.paymentMethodsErrorResponse();
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllPaymentMethodsOKTest(){
		TodoPagoConector tpc=mock(TodoPagoConector.class);
		
		when(tpc.discoverPaymentMethods()).thenReturn(DPMOKResponse);
		
		assertEquals(true,tpc.discoverPaymentMethods().containsKey("PaymentMethodsCollection"));
		
		Map<String,Object> pmc=(HashMap<String,Object>)tpc.discoverPaymentMethods().get("PaymentMethodsCollection");
		Map<String,Object> pm=(HashMap<String,Object>)pmc.get("PaymentMethod");
		
		assertEquals(true,pm.containsKey("Id"));
		assertEquals(true,pm.containsKey("Name"));
		assertEquals(true,pm.containsKey("Logo"));
		
		assertEquals(1,pm.get("Id"));
		assertEquals("AMEX",pm.get("Name"));
		assertEquals("http://10.123.4.71:8092/images/AMEX.png",pm.get("Logo"));	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllPaymentMethodsErrorTest(){
		TodoPagoConector tpc=mock(TodoPagoConector.class);
		
		when(tpc.discoverPaymentMethods()).thenReturn(DPMErrorResponse);
		
		assertEquals(true,tpc.discoverPaymentMethods().containsKey("PaymentMethodsCollection"));
		
		Map<String,Object> pmc=(HashMap<String,Object>)tpc.discoverPaymentMethods().get("PaymentMethodsCollection");
		
		assertNull(pmc);
	}
}