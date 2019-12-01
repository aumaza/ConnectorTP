package mocks;

import java.util.Map;

import ar.com.todopago.api.rest.GoogleRestTodoPago;

public class GoogleRestTodoPagoMock extends GoogleRestTodoPago{

	private Map<String, String> mockData;
	
	public GoogleRestTodoPagoMock(String paramsBilling, String paramsShipping,Map<String, String> data) {
		super(paramsBilling, paramsShipping);
		this.mockData=data;
	}
	
	public Map<String, String> processData(Map<String, String> fraudControl){
		return this.mockData;
	}
}