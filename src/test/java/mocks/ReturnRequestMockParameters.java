package mocks;

import java.util.Map;

import ar.com.todopago.api.ElementNames;

public class ReturnRequestMockParameters {
	
	public static Map<String, String> getRRParameters() {
		Map<String, String> parameters = VoidRequestMockParameters.getVRParameters();
		parameters.put(ElementNames.Amount, "10.5");
		return parameters;
	}
	
	public static Map<String, Object> getRROKResponse(){
		
		return VoidRequestMockParameters.getVROKResponse();
	}
	
	public static Map<String, Object> getRRErrorResponse(){
		
		return VoidRequestMockParameters.getVRErrorResponse();
	}
	
	public static Map<String, Object> getRRError702Response(){
		
		return VoidRequestMockParameters.getVRError702Response();
	}
}