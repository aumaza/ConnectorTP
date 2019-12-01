package mocks;

import ar.com.todopago.api.ElementNames;

import java.util.HashMap;
import java.util.Map;

public class GetStatusMockParameters{
	public static Map<String, String> getStatusParameters(){
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put(ElementNames.Merchant,TodoPagoParametersMock.MERCHANT);
                parameters.put(ElementNames.OperationID, "8001");
		return parameters;
	}
	
	public static Map<String, Object> getStatusOKResponse(){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("OperationsCollections",operationsColections());
	
		return parameters;
	}
	
	public static Map<String, Object> getStatusErrorResponse(){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("OperationsCollections",null);
	
		return parameters;
	}
	
	/*---- Auxiliary methods ----*/
	public static Map<String,Object> operationsColections(){
		Map<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("RESULTCODE", -1);
		parameters.put("RESULTMESSAGE", "APROBADA");
		parameters.put("DATETIME", "2015-05-13T14:11:38.287+00:00");
		parameters.put("OPERATIONID",01);
		parameters.put("CURRENCYCODE", 32);
		parameters.put("AMOUNT", 54);
		parameters.put("TYPE", "compra_online");
		parameters.put("INSTALLMENTPAYMENTS", 4);
		parameters.put("CUSTOMEREMAIL", "jose@perez.com");
		parameters.put("IDENTIFICATIONTYPE", "DNI");
		parameters.put("IDENTIFICATION", 1212121212);
		parameters.put("CARDNUMBER", "12121212XXXXXX1212");
		parameters.put("IDENTIFICATION", 1212121212);
		parameters.put("CARDHOLDERNAME","Jose Perez");
		parameters.put("TICKETNUMBER", 0);
		parameters.put("AUTHORIZATIONCODE", null);
		parameters.put("BARCODE", null);
		parameters.put("COUPONEXPDATE", null);
		parameters.put("COUPONSECEXPDATE", null);
		parameters.put("COUPONSUBSCRIBER", null);
		
		return parameters;
	}
}
