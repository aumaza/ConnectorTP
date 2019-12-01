package main;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import ar.com.todopago.api.ElementNames;
import ar.com.todopago.api.TodoPagoConector;
import ar.com.todopago.api.exceptions.ConnectionException;
import ar.com.todopago.api.exceptions.EmptyFieldException;
import ar.com.todopago.api.exceptions.ResponseException;
//import ar.com.todopago.api.model.GBRDTParameters;
import ar.com.todopago.api.model.User;
//import ar.com.todopago.api.rest.GoogleRest;
//import ar.com.todopago.api.rest.GoogleRestTodoPago;
//import mocks.GetByRangeDateTimeMockParameters;


/**
 * @author juan.peregrina
 *
 */
public class Test {

	// Verticales para CS
	public final static int RETAIL = 0;
	public static int vertical = RETAIL;// Configurar vertical a usar
	private final static Logger logger = Logger.getLogger(Test.class.getName());

	public static final String APIKEY = "TODOPAGO 45025C1E041EF13706C3992340A4393D";
	public static final String MERCHANT = "89118";
	public static final String SECURITY = "45025C1E041EF13706C3992340A4393D";

	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {

		//boolean overrideSSL = true;

		// Production
		// TodoPagoConector tpc = new
		// TodoPagoConector(TodoPagoConector.productionEndpoint,
		// getAuthorization());

		// Developer
		 TodoPagoConector tpc = new TodoPagoConector(TodoPagoConector.developerEndpoint, getAuthorization());

		// Developer without APYKey
		//TodoPagoConector tpc = new TodoPagoConector(TodoPagoConector.productionEndpoint);

		getCredentials(tpc);

		Map<String, Object> a = tpc.sendAuthorizeRequest(getSARParameters(), getFraudControlParameters());
		printMap(a, "");

		Map<String, Object> b = tpc.getAuthorizeAnswer(getAAParameters());
		printMap(b, "");

		Map<String, Object> e = tpc.getStatus(getSParameters());
		printMap(e, "");

		Map<String, Object> f = tpc.getAllPaymentMethods(getPMParameters());
		System.out.println(f);
		printMap(f, "");

		Map<String, Object> g = tpc.discoverPaymentMethods();
		System.out.println(g);
		printMap(g, "");

		Map<String, Object> h = tpc.voidRequest(getVRParameters());
		System.out.println(h);
		printMap(h, "");

		Map<String, Object> i = tpc.returnRequest(getRRParameters());
		System.out.println(i);
		printMap(i, "");

		Map<String, Object> j = tpc.getByRangeDateTime(getBRYParameters());
		//Map<String, Object> j = tpc.getByRangeDateTime(new GBRDTParameters(Test.MERCHANT,"1",GetByRangeDateTimeMockParameters.STARTDATEWITHHOUR,GetByRangeDateTimeMockParameters.ENDDATEWITHHOUR));
		System.out.println(j);
		printMap(j, "");

	}

	private static void printMap(Map<String, Object> pr, String tab) {

		List<String> keys = new ArrayList<String>();
		keys.addAll(pr.keySet());

		for (int i = 0; i < keys.size(); i++) {
			Map<String, Object> aux = new HashMap<String, Object>();
			Object tmp = pr.get(keys.get(i));

			if (tmp != null && tmp.getClass().isInstance(aux)) {
				System.out.println(tab + "- " + keys.get(i));
				aux = (Map<String, Object>) (tmp);
				printMap(aux, tab + "  ");

			} else {
				System.out.println(tab + "- " + keys.get(i) + " : " + pr.get(keys.get(i)));
			}
		}
	}

	private static Map<String, String> getSARParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ElementNames.Session, "ABCDEF-1234-12221-FDE1-00000200");
		parameters.put(ElementNames.Security, SECURITY);
		parameters.put(ElementNames.EncodingMethod, "XML");
		parameters.put(ElementNames.Merchant, MERCHANT);
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

		// Datos Opcionales:
		// parameters.put("AVAILABLEPAYMENTMETHODSIDS", "1#194#43#45");
		// parameters.put("PUSHNOTIFYENDPOINT", "");
		// parameters.put("PUSHNOTIFYMETHOD", "");
		// parameters.put("PUSHNOTIFYSTATES", "");

		return parameters;
	}

	// Parametros para el control de fraude
	private static Map<String, String> getFraudControlParameters() {
		// Example
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("CSBTCITY", "villa general belgrano"); // MANDATORIO.
		parameters.put("CSBTCOUNTRY", "AR");// MANDATORIO. C�digo ISO.
		parameters.put("CSBTEMAIL", "some@someurl.com"); // MANDATORIO.
		parameters.put("CSBTFIRSTNAME", "juan");// MANDATORIO.
		parameters.put("CSBTLASTNAME", "Perez");// MANDATORIO.
		parameters.put("CSBTPHONENUMBER", "541160913988");// MANDATORIO.
		parameters.put("CSBTPOSTALCODE", "1010");// MANDATORIO.
		parameters.put("CSBTSTATE", "B");// MANDATORIO
		parameters.put("CSBTSTREET1", "Some Street 2153");// MANDATORIO.
		parameters.put("CSBTSTREET2", "Test");// NO MANDATORIO

		parameters.put("CSBTCUSTOMERID", "453458"); // MANDATORIO.
		parameters.put("CSBTIPADDRESS", "192.0.0.4"); // MANDATORIO.
		parameters.put("CSPTCURRENCY", "ARS");// MANDATORIO.
		parameters.put("CSPTGRANDTOTALAMOUNT", "10.01");// MANDATORIO.

		parameters.put("CSMDD6", "");// NO MANDATORIO.
		parameters.put("CSMDD7", "");// NO MANDATORIO.
		parameters.put("CSMDD8", "");// NO MANDATORIO.
		parameters.put("CSMDD9", "");// NO MANDATORIO.
		parameters.put("CSMDD10", "");// NO MANDATORIO.
		parameters.put("CSMDD11", "");// NO MANDATORIO.

		switch (vertical) {
		case RETAIL:
			setRetail(parameters);
			break;
		}

		return parameters;
	}

	private static void setRetail(Map<String, String> parameters) {
		parameters.put("CSSTCITY", "villa general belgrano"); // MANDATORIO.
		parameters.put("CSSTCOUNTRY", "AR");// MANDATORIO. C�digo ISO.
		parameters.put("CSSTEMAIL", "some@someurl.com"); // MANDATORIO.
		parameters.put("CSSTFIRSTNAME", "Juan");// MANDATORIO.
		parameters.put("CSSTLASTNAME", "Perez");// MANDATORIO.
		parameters.put("CSSTPHONENUMBER", "541160913988");// MANDATORIO.
		parameters.put("CSSTPOSTALCODE", " 1010");// MANDATORIO.
		parameters.put("CSSTSTATE", "B");// MANDATORIO
		parameters.put("CSSTSTREET1", "Some Street 2153");// MANDATORIO.
		parameters.put("CSSTSTREET2", "Test");// NO MANDATORIO.

		parameters.put("CSITPRODUCTCODE", "electronic_good");// CONDICIONAL
		parameters.put("CSITPRODUCTDESCRIPTION", "Test Prd Description");// CONDICIONAL.
		parameters.put("CSITPRODUCTNAME", "TestPrd");// CONDICIONAL.
		parameters.put("CSITPRODUCTSKU", "SKU1234");// CONDICIONAL.
		parameters.put("CSITTOTALAMOUNT", "10.01");// CONDICIONAL.
		parameters.put("CSITQUANTITY", "1");// CONDICIONAL.
		parameters.put("CSITUNITPRICE", "10.01");

		parameters.put("CSMDD12", "");// NO MADATORIO.
		parameters.put("CSMDD13", "");// NO MANDATORIO.
		parameters.put("CSMDD14", "");// NO MANDATORIO.
		parameters.put("CSMDD15", "");// NO MANDATORIO.
		parameters.put("CSMDD16", "");// NO MANDATORIO.
	}

	private static Map<String, String> getAAParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ElementNames.Security, SECURITY);
		parameters.put(ElementNames.Merchant, MERCHANT);
		parameters.put(ElementNames.Session, null);
		parameters.put(ElementNames.RequestKey, "710268a7-7688-c8bf-68c9-430107e6b9da");
		parameters.put(ElementNames.AnswerKey, "693ca9cc-c940-06a4-8d96-1ab0d66f3ee6");
		return parameters;
	}

	private static Map<String, String> getPMParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ElementNames.Merchant, MERCHANT);
		return parameters;
	}

	private static Map<String, String> getSParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ElementNames.Merchant, MERCHANT);
		parameters.put(ElementNames.OperationID, "8001");
		return parameters;
	}

	private static Map<String, String> getVRParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ElementNames.Security, SECURITY);
		parameters.put(ElementNames.Merchant, MERCHANT);
		parameters.put(ElementNames.RequestKey, "e81d4bbf-e9a4-72b3-95fc-d66e88a45012");
		return parameters;
	}

	private static Map<String, String> getRRParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ElementNames.Security, SECURITY);
		parameters.put(ElementNames.Merchant, MERCHANT);
		parameters.put(ElementNames.RequestKey, "e81d4bbf-e9a4-72b3-95fc-d66e88a45012");
		parameters.put(ElementNames.Amount, "0.5");
		return parameters;
	}

	private static Map<String, String> getBRYParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ElementNames.Merchant, MERCHANT);
		parameters.put(ElementNames.STARTDATE, "2017-01-01");
		parameters.put(ElementNames.ENDDATE, "2017-01-13");
		parameters.put(ElementNames.PAGENUMBER, "1");
		return parameters;
	}

	private static void healthCheck(TodoPagoConector tpc) {

		Boolean check = tpc.healthCheck();
		
		if(check){
			System.out.println("Service available");			
		}else{
			System.out.println("Service not available");
		}

	}

	private static User getUser() {
		String mail = "test@test.com.ar"; // The email is only as example
		String pass = "test1234"; // The pass is only as example
		User user = new User(mail, pass);
		return user;
	}
	
	private static void getCredentials(TodoPagoConector tpc) {

		User user = new User();
		try {

			user = tpc.getCredentials(getUser());
			tpc.setAuthorize(getAuthorization(user));

		} catch (EmptyFieldException e) {
			logger.log(Level.WARNING, e.getMessage());
		} catch (MalformedURLException e) {
			logger.log(Level.WARNING, e.getMessage());
		} catch (ResponseException e) {
			logger.log(Level.WARNING, e.getMessage());
		} catch (ConnectionException e) {
			logger.log(Level.WARNING, e.getMessage());
		}

		System.out.println(user.toString());
	}

	private static Map<String, List<String>> getAuthorization() {
		Map<String, List<String>> parameters = new HashMap<String, List<String>>();
		parameters.put(ElementNames.Authorization, Collections.singletonList(APIKEY));
		// include all aditional Http headers to map, all of them will be used
		return parameters;
	}

	private static Map<String, List<String>> getAuthorization(User user) {
		Map<String, List<String>> parameters = new HashMap<String, List<String>>();
		parameters.put(ElementNames.Authorization, Collections.singletonList(user.getApiKey()));
		// include all aditional Http headers to map, all of them will be used
		return parameters;
	}

}
