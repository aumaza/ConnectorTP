package mocks;

import java.util.HashMap;
import java.util.Map;

import ar.com.todopago.api.ElementNames;

public class SARMockParameters {
	
	public static Map<String, String> getSARParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		
		parameters.put(ElementNames.Session, "ABCDEF-1234-12221-FDE1-00000200");
		parameters.put(ElementNames.Security, TodoPagoParametersMock.SECURITY);
		parameters.put(ElementNames.EncodingMethod, "XML");
		parameters.put(ElementNames.Merchant, TodoPagoParametersMock.MERCHANT);
		parameters.put(ElementNames.OperationID, "8001");
		parameters.put(ElementNames.CurrencyCode, "032");
		parameters.put(ElementNames.Amount, "1.00");
		parameters.put(ElementNames.UrlOK, "http,//someurl.com/ok/");
		parameters.put(ElementNames.UrlError, "http,//someurl/fail/");
		parameters.put(ElementNames.EMAILCLIENTE, "some@someurl.com");
		parameters.put(ElementNames.MAXINSTALLMENTS, "12");
		parameters.put(ElementNames.MININSTALLMENTS, "1");
		parameters.put(ElementNames.TIMEOUT, "1800000");
		parameters.put(ElementNames.DISTRIBUTEDMERCHANT, "3#20");
		parameters.put(ElementNames.DISTRIBUTEDAMOUNT, "10.00#15.00");

		return parameters;
	}
	
	public static Map<String, Object> getResponseSarOK(){
			
		Map<String, Object> result=new  HashMap<String, Object>();
		
		result.put("StatusCode", -1);
		result.put("PublicRequestKey", "te0b9bba5-cff9-173a-20da-b9bc8a389ac7");
		result.put("URL_Request", "https://developers.todopago.com.ar/formulario/commands?command=formulario&m=te0b9bba5-cff9-173a-20da-b9bc8a389ac7");
		result.put("StatusMessage", "Solicitud de Autorizacion Registrada");
		result.put("RequestKey", "ff0f6434-a2ab-e87f-3ece-37f7081e671a");
		
		return result;
	}
	
	public static Map<String, Object> getResponseSarError(){		
		return GenericResponse.genericResponse(980001,"El campo CSBTCITY es obligatorio. (Min Length 2)");	
	}
	
	public static Map<String, Object> getResponseSar702Error(){
		return GenericResponse.genericResponse(702,"ERROR: Cuenta Inexistente");	
	}
}
