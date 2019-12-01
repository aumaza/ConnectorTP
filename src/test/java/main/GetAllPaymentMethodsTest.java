package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ar.com.todopago.api.TodoPagoConector;
import mocks.PaymentsMethodsMockParameters;

public class GetAllPaymentMethodsTest{

	Map<String,String> GAPMParameters=PaymentsMethodsMockParameters.getPMParameters();
	
	Map<String,Object> GAPMOKResponse=PaymentsMethodsMockParameters.paymentMethodsOKResponse(true);
	Map<String,Object> GAPMErrorResponse=PaymentsMethodsMockParameters.paymentMethodsErrorResponse();
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllPaymentMethodsOKTest(){
		TodoPagoConector tpc=mock(TodoPagoConector.class);
		
		when(tpc.getAllPaymentMethods(GAPMParameters)).thenReturn(GAPMOKResponse);
		
		assertEquals(true,tpc.getAllPaymentMethods(GAPMParameters).containsKey("PaymentMethodsCollection"));
		
		Map<String,Object> pmc=(HashMap<String,Object>)tpc.getAllPaymentMethods(GAPMParameters).get("PaymentMethodsCollection");
		Map<String,Object> pm=(HashMap<String,Object>)pmc.get("PaymentMethod");
		
		assertEquals(true,pm.containsKey("Id"));
		assertEquals(true,pm.containsKey("Name"));
		assertEquals(true,pm.containsKey("Logo"));
		
		assertEquals(1,pm.get("Id"));
		assertEquals("AMEX",pm.get("Name"));
		assertEquals("http://10.123.4.71:8092/images/AMEX.png",pm.get("Logo"));
		assertEquals(true,pm.get("ExpirationDateCheck"));
		
		assertEquals(true,pm.get("ExpirationDateCheck"));
		assertEquals(4,pm.get("SecurityCodeLength"));
		assertEquals(true,pm.get("SecurityCodeCheck"));
		assertEquals(15,pm.get("CardNumberLengthMax"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllPaymentMethodsErrorTest(){
		TodoPagoConector tpc=mock(TodoPagoConector.class);
		
		when(tpc.getAllPaymentMethods(GAPMParameters)).thenReturn(GAPMErrorResponse);
		
		assertEquals(true,tpc.getAllPaymentMethods(GAPMParameters).containsKey("PaymentMethodsCollection"));
		
		Map<String,Object> pmc=(HashMap<String,Object>)tpc.getAllPaymentMethods(GAPMParameters).get("PaymentMethodsCollection");
		
		assertNull(pmc);
	}
}