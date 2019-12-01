package mocks;

import java.util.HashMap;
import java.util.Map;

import ar.com.todopago.api.ElementNames;

public class VoidRequestMockParameters{
	
	public static Map<String, String> getVRParameters(){
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ElementNames.Security,TodoPagoParametersMock.SECURITY); // API Key del comercio asignada por TodoPago
		parameters.put(ElementNames.Merchant, TodoPagoParametersMock.MERCHANT);// Merchant o Nro de comercio asignado por TodoPago
		parameters.put(ElementNames.RequestKey, "e31d340c-690c-afe6-c478-fc1bef3fc157");  // RequestKey devuelto como respuesta del servicio SendAutorizeRequest
		return parameters;
	}
	
	public static Map<String, Object> getVROKResponse(){		
		return GenericResponse.genericResponse(2011,"Operación realizada correctamente");
	}
	
	public static Map<String, Object> getVRErrorResponse(){
		return GenericResponse.genericResponse(2013,"No es posible obtener los importes de las comisiones para realizar la devolucion");
	}
	
	public static Map<String, Object> getVRError702Response(){		
		return GenericResponse.genericResponse(702,"Cuenta de Vendedor Invalida");
	}
}