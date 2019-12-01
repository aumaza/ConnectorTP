package ar.com.todopago.api.rest;

import java.io.InputStream;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class GoogleRest extends RestConnector{

	protected JSONArray googleData;

	private String urlParams;

	private  String key;

	public GoogleRest(String url,String key) throws UnsupportedEncodingException {
		super("",null);

		this.googleData=null;
		
		this.key=key;

		this.urlParams="https://maps.googleapis.com/maps/api/geocode/json?address="+ URLEncoder.encode(url, "UTF-8")+"&key="+this.key;
	}

	public JSONArray getGoogleAdressData() throws JSONException{
		JSONArray jsonArray=null;

		if(this.googleData==null){
			String indexName="results";
			Map<String,Object> googleData=getGoogleJsonData();
			JSONObject jsonObject0=(JSONObject)googleData.get(indexName);
			jsonArray=jsonObject0.getJSONArray(indexName);
			this.googleData=jsonArray;
		}else{
			jsonArray=this.googleData;
		}

		return jsonArray;
	}
	
	public JSONArray getAdressComponents() {
		JSONArray result=null;
		
		try{
			result=this.getGoogleAdressData().getJSONObject(0).getJSONArray("address_components");
		}catch(Exception e){
			logger.log(Level.SEVERE, "Error on get", e.getMessage() + " - " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	//Auxiliary methods
	private Map<String,Object> getGoogleJsonData(){
		
		Map<String,Object> result=null;
		
		try{
			InputStream is=sendGet(this.urlParams,true);
			
			result=OperationsParser.parseInputStreamJsonGoogleToMap(is);
			
			return result;
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "Error on get", e.getMessage() + " - " + e.getLocalizedMessage());
			e.printStackTrace();
		} 
		
		return result;
	}	
}