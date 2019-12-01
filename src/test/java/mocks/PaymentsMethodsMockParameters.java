package mocks;

import java.util.HashMap;
import java.util.Map;

public class PaymentsMethodsMockParameters {
	
	public static Map<String, String> getPMParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		
		parameters.put("MERCHANT",TodoPagoParametersMock.MERCHANT);
		parameters.put("OPERATIONID","185");

		return parameters;
	}
	
	public static Map<String,Object> paymentMethodsOKResponse(boolean paymentCollectionComplete){
		Map<String,Object> pm=new HashMap<String,Object>();
		
		pm.put("PaymentMethodsCollection",paymentMethodsCollection(paymentCollectionComplete));
		
		return pm;
	}
	
	public static Map<String,Object> paymentMethodsErrorResponse(){
		Map<String,Object> pm=new HashMap<String,Object>();
		
		pm.put("PaymentMethodsCollection",null);
		
		return pm;
	}
	
	private static Map<String,Object> paymentMethodsCollection(boolean paymentCollecionComplete) {
		
		Map<String,Object> pmc=new HashMap<String, Object>();
		Map<String,Object> pm0=new HashMap<String, Object>();
		
		pmc.put("PaymentMethod",pm0);
		
		pm0.put("Id",1);
		pm0.put("Logo","http://10.123.4.71:8092/images/AMEX.png");
		pm0.put("Name","AMEX");
		
		if(paymentCollecionComplete){
			pm0.put("Id",1);
			pm0.put("IdTipoMedioPago",1);
			pm0.put("IdTipoMedioPago",1);
			pm0.put("Name","AMEX");
			pm0.put("Logo","http://10.123.4.71:8092/images/AMEX.png");
			pm0.put("ExpirationDateCheck",true);
			pm0.put("SecurityCodeLength",4);
			pm0.put("SecurityCodeCheck",true);
			pm0.put("CardNumberLengthMax",15);
			pm0.put("CardNumberLengthMin",1);
			pm0.put("CurrenciesCollection",currenciesCollection());
		}
		
		return pmc;
	}

	private static Map<String,Object> currenciesCollection() {
		Map<String,Object> currenciesCollection=new HashMap<String, Object>();
		currenciesCollection.put("currency",currency());
		
		return currenciesCollection;
	}

	private static Map<String,Object> currency() {
		Map<String,Object> currency=new HashMap<String, Object>();
		currency.put("Id",032);
		currency.put("Name","Peso Argentino");
		
		return currency;
	}
}