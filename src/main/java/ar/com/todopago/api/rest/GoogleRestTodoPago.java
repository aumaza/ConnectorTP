package ar.com.todopago.api.rest;

import java.io.UnsupportedEncodingException;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleRestTodoPago{
	
	private GoogleRest googleDataBilling;
	private GoogleRest googleDataShipping;
	private String key="";
	
	public GoogleRestTodoPago(String paramsBilling,String paramsShipping){
		try {
			this.googleDataBilling=new GoogleRest(paramsBilling,this.key);
			this.googleDataShipping=new GoogleRest(paramsShipping,this.key);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public GoogleRest getGoogleDataBilling() throws JSONException{
		return this.googleDataBilling;
	}
	
	public GoogleRest getGoogleDataShipping() throws JSONException{
		return this.googleDataShipping;
	}
	
	public Map<String, String> processData(Map<String, String> fraudControl){		
		
		try{
			JSONArray googleRestBilling=this.getGoogleDataBilling().getAdressComponents();
			JSONArray googleRestShipping=this.getGoogleDataShipping().getAdressComponents();
			fraudControl=this.iterateFraudControl(googleRestBilling, fraudControl,"B");
			fraudControl=this.iterateFraudControl(googleRestShipping, fraudControl, "S");
			
		}catch(JSONException exception){
			exception.printStackTrace();
		}
		
		return fraudControl;
	}
	
	private Map<String, String> iterateFraudControl(JSONArray data,Map<String, String> fraudControl,String letter){
		
		try{
			
			String street="";
			String streetNumber="";
			
			for (int i = 0; i < data.length(); i++) {
				
				JSONObject actual=data.getJSONObject(i);
				JSONArray actualArray=actual.getJSONArray("types");
				
				if(this.hasValue(actualArray,"route")){//Arreglar
					street=actual.getString("long_name");
				}
				
				if(this.hasValue(actualArray,"street_number")){//Arreglar
					streetNumber=actual.getString("long_name");
				}
				
				if(this.hasValue(actualArray,"locality")){
					fraudControl.put("CS"+letter+"TCITY",actual.getString("long_name"));
				}
				
				if(this.hasValue(actualArray,"administrative_area_level_1")){
					fraudControl.put("CS"+letter+"TSTATE",getProvincia(actual.getString("short_name")));
				}
				
				if(this.hasValue(actualArray,"country")){
					fraudControl.put("CS"+letter+"TCOUNTRY",actual.getString("short_name"));
				}
				
				if(this.hasValue(actualArray,"postal_code")){
					fraudControl.put("CS"+letter+"TPOSTALCODE",actual.getString("long_name"));
				}
					
			}
			
			if(!street.equals("") && !streetNumber.equals("")){
				fraudControl.put("CS"+letter+"TSTREET1",street+" "+streetNumber);
			}
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return fraudControl;
	
	}

	private String getProvincia(String value) {
	HashMap myMap = new HashMap<String, String>();
        myMap.put("CABA","C");
        myMap.put("Buenos Aires","B");
        myMap.put("Córdoba","X");
        myMap.put("Cordoba","X");
        myMap.put("Santa Fe","S");
        myMap.put("Santa Cruz","Z");
        myMap.put("San Juan","J");
        myMap.put("La Rioja","F");
        myMap.put("La Pampa","L");
        myMap.put("Entre Ríos","E");
        myMap.put("Entre Rios","E");
        myMap.put("Catamarca","K");
        myMap.put("Chaco","H");
        myMap.put("Chubut","U");
        myMap.put("Corrientes","W");
        myMap.put("Formosa","P");
        myMap.put("Jujuy","Y");
        myMap.put("Mendoza","M");
        myMap.put("Misiónes","N");
        myMap.put("Misiones","N");
        myMap.put("Neuquén","Q");
        myMap.put("Neuquen","Q");
        myMap.put("Río Negro","R");
        myMap.put("Rio Negro","R");
        myMap.put("Salta","A");
        myMap.put("San Luis","D");
        myMap.put("Santiago del Estero", "G");
        myMap.put("Tierra del Fuego","V");
        myMap.put("Tucumán","T");
        myMap.put("Tucuman","T");

	String result = (String) myMap.get(value);
	return result;
	}

	private boolean hasValue(JSONArray actualArray, String value) throws JSONException {
		
		boolean exists=false;

		for (int i = 0; i < actualArray.length(); i++){
			if(actualArray.get(i).equals(value)){
				exists=true;
			}
		}
		
		return exists;
	}
}
