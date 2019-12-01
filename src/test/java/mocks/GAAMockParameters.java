package mocks;

import java.util.HashMap;
import java.util.Map;

import ar.com.todopago.api.ElementNames;

public class GAAMockParameters {

	public static Map<String, String> getAAParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ElementNames.Security, TodoPagoParametersMock.SECURITY);
		parameters.put(ElementNames.Merchant, TodoPagoParametersMock.MERCHANT);
		parameters.put(ElementNames.Session, null);
		parameters.put(ElementNames.RequestKey, "710268a7-7688-c8bf-68c9-430107e6b9da");
		parameters.put(ElementNames.AnswerKey, "693ca9cc-c940-06a4-8d96-1ab0d66f3ee6");
		return parameters;
	}
	
	public static Map<String, Object> getAuthorizeAnswerOKResponse() {
		Map<String, Object> result=new  HashMap<String, Object>();
		
		result.put("StatusCode", -1);
		result.put("StatusMessage", "APROBADA");
		result.put("AuthorizationKey", "1294-329E-F2FD-1AD8-3614-1218-2693-1378");
		result.put("EncodingMethod","XML");
		result.put("Payload",PayloadParameters());
		
		return result;
	}
	
	public static Map<String, Object> getAuthorizeAnswerErrorResponse() {
		return GenericResponse.genericResponse(404,"ERROR: Transaccion Enexistente");
	}
	
	public static Map<String, Object> getAuthorizeAnswerError702Response() {
		Map<String, Object> result=new  HashMap<String, Object>();
		
		result.put("StatusCode", 702);
		result.put("StatusMessage", "Cuenta de Vendedor Invalida");
		
		return GenericResponse.genericResponse(702,"Cuenta de Vendedor Invalida");
	}

	/*---- Auxiliary methods ----*/
	
	private static Map<String, Object> PayloadParameters(){
		Map<String, Object> parameters=new HashMap<String, Object>();
		
		parameters.put("Answer",AnswerResponse());
		parameters.put("Answer",RequestResponse());
		
		return parameters;
	}
	
	private static Map<String, Object> AnswerResponse() {
		Map<String, Object> result=new  HashMap<String, Object>();
		
		result.put("DATETIME", "2014/08/11 15:24:38");
		result.put("RESULTCODE", -1);
		result.put("RESULTMESSAGE", "APROBADA");
		result.put("CURRENCYNAME","Pesos");
		result.put("PAYMENTMETHODNAME","VISA");
		
		result.put("TICKETNUMBER", "12");
		result.put("CARDNUMBERVISIBLE","450799******4905");
		result.put("AUTHORIZATIONCODE","TEST38");
		
		result.put("INSTALLMENTPAYMENTS", "5");
		
		return result;
	}
	
	private static Map<String, Object> RequestResponse() {
		Map<String, Object> result=new  HashMap<String, Object>();
		
		result.put("MERCHANT", "12345678");
		result.put("OPERATIONID", "ABCDEF-1234-12221-FDE1-00000012");
		result.put("AMOUNT", "1.00");
		result.put("CURRENCYCODE","032");
		result.put("AMOUNTBUYER","1.10");
		
		return result;
	}
	
}