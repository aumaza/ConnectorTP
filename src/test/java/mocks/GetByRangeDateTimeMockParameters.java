package mocks;

import ar.com.todopago.api.ElementNames;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class GetByRangeDateTimeMockParameters{
	
	public static final Date STARTDATEWITHHOUR=new GregorianCalendar(2017,2,3,16,15).getTime();//2017-03-03T16:15:00
	public static final Date ENDDATEWITHHOUR=new GregorianCalendar(2017,7,7,22,34).getTime();//2017-08-07T22:34:00
	public static final String PAGENUMBER="1";
	
	public static Map<String, String> getBRYParameters(String startDate,String endDate){
	    Map<String, String> parameters = new HashMap<String, String>();
	    parameters.put(ElementNames.Merchant,TodoPagoParametersMock.MERCHANT);
	    parameters.put(ElementNames.STARTDATE,startDate);
	    parameters.put(ElementNames.ENDDATE,endDate);
	    parameters.put(ElementNames.PAGENUMBER, "1");
	    return parameters;
	}
	
	public static Map<String, Object> getBRYOKResponse(){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("OperationsCollections",operationsColections());
	
		return parameters;
	}
	
	public static Map<String, Object> getBRYModifiedOKResponse(){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("OperationsCollections",operationsColections());
	
		return parameters;
	}
	
	/*---- Auxiliary methods ----*/
	private static Map<String,Object> operationsColections() {
		Map<String,Object> parameters=GetStatusMockParameters.operationsColections();
		parameters.put("BANKID", 11);
		parameters.put("PAYMENTMETHODTYPE", "Crédito");
		parameters.put("PAYMENTMETHODCODE", 42);
		parameters.put("PROMOTIONID", 2706);
		parameters.put("AMOUNTBUYER", 10.00);
		parameters.put("PAYMENTMETHODNAME", "VISA");
		parameters.put("PUSHNOTIFYENDPOINT", null);
		parameters.put("PUSHNOTIFYMETHOD", null);
		parameters.put("PUSHNOTIFYSTATES", null);	
		parameters.put("REFUNDED", null);
		parameters.put("REFUNDS", refunds());
		
		return parameters;
	}

	private static Map<String,Object> refunds() {
		Map<String,Object> refunds=new HashMap<String, Object>();
		Map<String,Object> refund0=new HashMap<String, Object>();
		refund0.put("ID","50163419");
		refund0.put("DATETIME","2016-03-18T16:03:54.987-03:00");
		refund0.put("AMOUNT",10.00);
		refunds.put("REFUND0",refund0);
		
		return refunds;
	}
}