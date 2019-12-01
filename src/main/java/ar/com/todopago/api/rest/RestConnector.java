package ar.com.todopago.api.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import ar.com.todopago.api.ElementNames;
import ar.com.todopago.api.exceptions.ConnectionException;
	
public class RestConnector {

	protected final static Logger logger = Logger.getLogger(RestConnector.class.getName());

	protected final String USER_AGENT = "Mozilla/5.0";
	protected String endpoint; 
	protected Map<String, String> headders;

	protected final String CONTENT_TYPE_APP_JSON = "application/json";
	protected final String METHOD_POST = "POST";
	protected final String METHOD_GET = "GET";
	
	public RestConnector(String endpoint, Map<String, List<String>> headder) {

		this.endpoint = endpoint;
		this.headders = new HashMap<String, String>();

		if (headder != null) {
			List<String> aux = headder.get(ElementNames.Authorization);
			this.headders.put(ElementNames.Authorization, aux.get(0));
		}
	}

	private HttpURLConnection generateHttpURLConnection(String url, String contentType, String method, Boolean withApiKey) throws IOException {

    	URL obj = new URL(url);
		HttpURLConnection client = (HttpURLConnection) obj.openConnection();
		
		client.setRequestProperty("User-Agent", USER_AGENT);

        if (method != null && !method.equals("")){
        	client.setRequestMethod(method);
        }else{
        	client.setRequestMethod(METHOD_POST);
        }
        
        if(client.getRequestMethod().equals(METHOD_POST)){
        	
            if (contentType != null && !contentType.equals("")){
            	client.setRequestProperty("Content-Type", contentType);
            } else {
            	client.setRequestProperty("Content-Type",CONTENT_TYPE_APP_JSON);
            }
            
        	client.setDoInput(true);
        	client.setDoOutput(true);
        	client.setUseCaches(false);
        }
        
        if (withApiKey) {
        	client.setRequestProperty(ElementNames.Authorization, headders.get("Authorization"));
		}

        return client;
   }
	
	private InputStream send(HttpURLConnection httpURLConnection)  throws IOException, ConnectionException {
		
		int responseCode = httpURLConnection.getResponseCode();

		if (responseCode != 200) {
			logger.log(Level.INFO, "Test: " + httpURLConnection.getErrorStream().toString());
			BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
			String output = br.readLine();
			throw new ConnectionException(responseCode + " - " + httpURLConnection.getResponseMessage() + " - " + output);
		}

		InputStream is = httpURLConnection.getInputStream();
		logger.log(Level.INFO, "Response Code : " + responseCode);

		return is;
	}
    
	public InputStream sendGet(String url,Boolean withApiKey) throws IOException, ConnectionException {

		HttpURLConnection con = generateHttpURLConnection(url, null, METHOD_GET, withApiKey);
	    


		InputStream is = send(con);
		logger.log(Level.INFO, "\nSending 'GET' request to URL : " + url);

		return is;
	}
    
	public InputStream sendGet(String url,Boolean withApiKey,Map<String,String> headers) throws IOException, ConnectionException {

		HttpURLConnection con = generateHttpURLConnection(url, null, METHOD_GET, withApiKey);
	    con.setRequestProperty("Accept", headers.get("Accept"));


		InputStream is = send(con);
		logger.log(Level.INFO, "\nSending 'GET' request to URL : " + url);

		return is;
	}
	
	public InputStream sendPost(String url, String json, Boolean withApiKey) throws IOException, ConnectionException {
		
		HttpURLConnection con = generateHttpURLConnection(url, CONTENT_TYPE_APP_JSON, METHOD_POST, withApiKey);
		
		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(json);
		wr.flush();

		InputStream is = send(con);
		logger.log(Level.INFO, "\nSending 'POST' request to URL : " + url);
		
		return is;
	}
    
    
    
    
    
    
	

}
