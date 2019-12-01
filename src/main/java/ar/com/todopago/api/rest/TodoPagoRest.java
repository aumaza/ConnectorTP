package ar.com.todopago.api.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import ar.com.todopago.api.ElementNames;
import ar.com.todopago.api.exceptions.ConnectionException;
import ar.com.todopago.api.exceptions.ResponseException;
import ar.com.todopago.api.model.GBRDTParameters;
import ar.com.todopago.api.model.User;

public class TodoPagoRest extends RestConnector{

	public TodoPagoRest(String endpoint, Map<String, List<String>> headder) {
		super(endpoint, headder);
	}
	
	private final static Logger logger = Logger.getLogger(RestConnector.class.getName());
	
    private final String AUTHORIZE = "Authorize";
    private final String CREDENTIALS = "Credentials";	
    private final String OPERATIONS_GET_BY_RANGE_DATE_TIME = "Operations/GetByRangeDateTime/";
    private final String PAYMENT_METHODS_GET = "PaymentMethods/Get/";
    private final String OPERATIONS_GET_BY_OPERATION_ID = "Operations/GetByOperationId/";  
    private final String PAYMENT_METHODS_DISCOVER = "PaymentMethods/Discover";
    
	public Map<String, Object> getByOperationId(Map<String, String> params) {

		// Tets amazon
		// url = "http://localhost:8280/t/1.1/api/Operations/GetByOperationId/MERCHANT/1/OPERATIONID/2502";
				
		StringBuilder sb = new StringBuilder(endpoint + OPERATIONS_GET_BY_OPERATION_ID);
		sb.append(ElementNames.Merchant.toUpperCase() + "/" + params.get(ElementNames.Merchant) + "/");
		sb.append(ElementNames.OperationID.toUpperCase() + "/" + params.get(ElementNames.OperationID));
		logger.log(Level.INFO, "URLCreation getByOperationId" + sb.toString());

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = OperationsParser.inputStreamToMap(sendGet(sb.toString(),true));
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error on get", e.getMessage() + " - " + e.getLocalizedMessage());
			result.put("Error", e.getMessage());
			result.put("StackTrace", e.getStackTrace());
		}
		return result;
	}

	public Map<String, Object> getPaymentMethods(Map<String, String> params) {
		
		StringBuilder sb = new StringBuilder(endpoint + PAYMENT_METHODS_GET);
		sb.append(ElementNames.Merchant.toUpperCase() + "/" + params.get(ElementNames.Merchant) + "/");	
		logger.log(Level.INFO, "URLCreation getPaymentMethods" + sb.toString());

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			
			PaymentMethodsParser pmp = new PaymentMethodsParser();
			result = pmp.streamToMap(sendGet(sb.toString(),true));

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error on get", e.getMessage() + " - " + e.getLocalizedMessage());
			result.put("Error", e.getMessage());
			result.put("StackTrace", e.getStackTrace());
		}

		return result;
	}

	public Map<String, Object> discoverPaymentMethods() {

		StringBuilder sb = new StringBuilder(endpoint + PAYMENT_METHODS_DISCOVER);
		logger.log(Level.INFO, "URLCreation getPaymentMethods" + sb.toString());

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PaymentMethodsParser pmp = new PaymentMethodsParser();
			result = pmp.streamToMap(sendGet(sb.toString(),true));

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error on get: ", e.getMessage());
			result.put("Error", e.getMessage());
			result.put("StackTrace", e.getStackTrace());
		}

		return result;
	}

	public Map<String, Object> getByRangeDateTime(Map<String, String> params) {

		// Tets Amazon
		// url =
		// "http://localhost:8280/t/1.1/api/Operations/GetByRangeDateTime/MERCHANT/1/STARTDATE/2016-03-16/ENDDATE/2016-03-17/PAGENUMBER/1";
		endpoint = endpoint.replace("t/1.1/", "t/1.2/");

		StringBuilder sb = new StringBuilder(endpoint + OPERATIONS_GET_BY_RANGE_DATE_TIME);
		sb.append(ElementNames.Merchant.toUpperCase() + "/" + params.get(ElementNames.Merchant) + "/");
		sb.append(ElementNames.STARTDATE.toUpperCase() + "/" + URLEncoder.encode(params.get(ElementNames.STARTDATE)) + "/");
		sb.append(ElementNames.ENDDATE.toUpperCase() + "/" + URLEncoder.encode(params.get(ElementNames.ENDDATE)) + "/");
		sb.append(ElementNames.PAGENUMBER.toUpperCase() + "/" + params.get(ElementNames.PAGENUMBER));
		logger.log(Level.INFO, "URLCreation GetByRangeDateTime " + sb.toString());

		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Accept","application/xml");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = OperationsParser.inputStreamToMap(sendGet(sb.toString(),true,headers));
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error on get", e.getMessage() + " - " + e.getLocalizedMessage());
			result.put("Error", e.getMessage());
			result.put("StackTrace", e.getStackTrace());
		}

		return result;

	}
	
	public Map<String, Object> getByRangeDateTime(GBRDTParameters parameters) {		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			endpoint = endpoint.replace("t/1.1/", "t/1.2/");

			StringBuilder sb = new StringBuilder(endpoint + OPERATIONS_GET_BY_RANGE_DATE_TIME);
			sb.append(ElementNames.Merchant.toUpperCase() + "/" + parameters.getMerchant() + "/");
			sb.append(ElementNames.STARTDATE.toUpperCase() + "/" + URLEncoder.encode(parameters.getStartDate(), "UTF-8") + "/");
			sb.append(ElementNames.ENDDATE.toUpperCase() + "/" + URLEncoder.encode(parameters.getEndDate(), "UTF-8") + "/");
			sb.append(ElementNames.PAGENUMBER.toUpperCase() + "/" + parameters.getPageNumber());
			logger.log(Level.INFO, "URLCreation GetByRangeDateTime " + sb.toString());
			
			result = OperationsParser.inputStreamToMap(sendGet(sb.toString(),true));
		} 
		catch (UnsupportedEncodingException e) {
			logger.log(Level.SEVERE, "Unsoported Encoding", e.getMessage() + " - " + e.getLocalizedMessage());
			result.put("Error", e.getMessage());
			result.put("StackTrace", e.getStackTrace());
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "Error on get", e.getMessage() + " - " + e.getLocalizedMessage());
			result.put("Error", e.getMessage());
			result.put("StackTrace", e.getStackTrace());
		}

		return result;
	}

	public Map<String, Object> getPromotions(Map<String, String> params) {
	
		StringBuilder sb = new StringBuilder(endpoint + PAYMENT_METHODS_GET);
		sb.append(ElementNames.Merchant.toUpperCase() + "/" + params.get(ElementNames.Merchant) + "/");	
		sb.append(ElementNames.PaymentMethod.toUpperCase() + "/" + params.get(ElementNames.PaymentMethod) + "/");	
		sb.append(ElementNames.Bank.toUpperCase() + "/" + params.get(ElementNames.Bank) + "/");	
		sb.append(ElementNames.Amount.toUpperCase() + "/" + params.get(ElementNames.Amount) + "/");			
		logger.log(Level.INFO, "URLCreation getPaymentMethods" + sb.toString());

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// result = PaymentMethodsParser.inputStreamToMap(sendGet(url));
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error on get", e.getMessage() + " - " + e.getLocalizedMessage());
			result.put("Error", e.getMessage());
			result.put("StackTrace", e.getStackTrace());
		}

		return result;
	}

	public Map<String, Object> voidRequest(Map<String, String> params) {

		
		StringBuilder sb = new StringBuilder(endpoint + AUTHORIZE);
		logger.log(Level.INFO, "URLCreation voidRequest " + sb.toString());

		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			JSONObject data = new JSONObject();

			data.put(ElementNames.RequestType, ElementNames.VoidRequest);
			data.put(ElementNames.Security, params.get(ElementNames.Security));
			data.put(ElementNames.RequestKey, params.get(ElementNames.RequestKey));
			data.put(ElementNames.Merchant, params.get(ElementNames.Merchant));

			if (params.containsKey(ElementNames.AuthorizationKey)) {
				data.put(ElementNames.AuthorizationKey, params.get(ElementNames.AuthorizationKey));
			}

			InputStream is = sendPost(sb.toString(), data.toString(), true);
			outMap = OperationsParser.parseInputStreamJsonToMap(is);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error on get", e.getMessage() + " - " + e.getLocalizedMessage());
			outMap.put("Error", e.getMessage());
			outMap.put("StackTrace", e.getStackTrace());
		}

		return outMap;

	}

	public Map<String, Object> returnRequest(Map<String, String> params) {

		StringBuilder sb = new StringBuilder(endpoint + AUTHORIZE);
		logger.log(Level.INFO, "URLCreation returnRequest " + sb.toString());

		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			JSONObject data = new JSONObject();

			data.put(ElementNames.RequestType, ElementNames.ReturnRequest);
			data.put(ElementNames.Security, params.get(ElementNames.Security));
			data.put(ElementNames.RequestKey, params.get(ElementNames.RequestKey));
			data.put(ElementNames.Merchant, params.get(ElementNames.Merchant));
			data.put(ElementNames.Amount, params.get(ElementNames.Amount));
			data.put(ElementNames.CurrencyCode, params.get(ElementNames.CurrencyCode));

			if (params.containsKey(ElementNames.AuthorizationKey)) {
				data.put(ElementNames.AuthorizationKey, params.get(ElementNames.AuthorizationKey));
			}

			InputStream is = sendPost(sb.toString(), data.toString(), true);
			outMap = OperationsParser.parseInputStreamJsonToMap(is);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error on get", e.getMessage() + " - " + e.getLocalizedMessage());
			outMap.put("Error", e.getMessage());
			outMap.put("StackTrace", e.getStackTrace());
		}

		return outMap;

	}

	public User getCredentials(User user) throws ResponseException, ConnectionException {

		String url = endpoint + CREDENTIALS;
		url = url.replace("t/1.1/", "");
		url = url.replace("t/1.2/", "");
		logger.log(Level.INFO, "URLCreation getCredentials " + url);
		User userResponse = new User();

		try {
			JSONObject data = new JSONObject();
			data.put(ElementNames.USUARIO, user.getUser());
			data.put(ElementNames.CLAVE, user.getPassword());

			InputStream is = sendPost(url, data.toString(), false);
			userResponse = OperationsParser.parseInputStreamJsonToUser(is);

			} catch (JSONException e) {
				logger.log(Level.SEVERE, "Error on get", e.getMessage() + " - " + e.getLocalizedMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Error on get", e.getMessage() + " - " + e.getLocalizedMessage());
				e.printStackTrace();
			} 
		
		return userResponse;
	}
}
