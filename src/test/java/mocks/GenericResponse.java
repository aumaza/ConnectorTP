package mocks;

import java.util.HashMap;
import java.util.Map;

public class GenericResponse {
	public static Map<String, Object> genericResponse(int statusCode,String statusMessage){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("StatusCode",statusCode);
		parameters.put("StatusMessage", statusMessage);
		
		return parameters;
	}
}