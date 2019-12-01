package mocks;

import ar.com.todopago.api.rest.GoogleRest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;

public class GoogleRestMock extends GoogleRest {

	private JSONArray googleData;
	
    public GoogleRestMock(String url,String key,JSONArray data) throws UnsupportedEncodingException, JSONException {
        super(url,key);
        this.googleData=data;
    }
    
    public JSONArray getGoogleAdressData() throws JSONException{
    	return this.googleData;
    }

    public static JSONArray validJsonResults() throws JSONException{
        String jsonString= "[{\"formatted_address\":\"157,, Av. Julio Argentino Roca 81, Villa Gral. Belgrano, Córdoba, Argentina\",\"types\":[\"bar\",\"establishment\",\"point_of_interest\"],\"partial_match\":true,\"geometry\":{\"viewport\":{\"southwest\":{\"lng\":-64.56059248029149,\"lat\":-31.9808230802915},\"northeast\":{\"lng\":-64.55789451970848,\"lat\":-31.9781251197085}},\"location\":{\"lng\":-64.5592435,\"lat\":-31.9794741},\"location_type\":\"ROOFTOP\"},\"address_components\":[{\"types\":[\"street_number\"],\"short_name\":\"81\",\"long_name\":\"81\"},{\"types\":[\"route\"],\"short_name\":\"Av. Julio Argentino Roca\",\"long_name\":\"Avenida Julio Argentino Roca\"},{\"types\":[\"locality\",\"political\"],\"short_name\":\"Villa Gral. Belgrano\",\"long_name\":\"Villa General Belgrano\"},{\"types\":[\"administrative_area_level_1\",\"political\"],\"short_name\":\"Córdoba\",\"long_name\":\"Córdoba\"},{\"types\":[\"country\",\"political\"],\"short_name\":\"AR\",\"long_name\":\"Argentina\"}],\"place_id\":\"ChIJ8XkLAEOw0pURYaNqB03sUJ8\"}]\n";
        return new JSONArray(jsonString);
    }

    public static JSONArray validJsonResults2() throws JSONException{
        String jsonString= "[{\"formatted_address\":\"Maipú 458, C1006ACD CABA, Argentina\",\"types\":[\"street_address\"],\"geometry\":{\"viewport\":{\"southwest\":{\"lng\":-58.37803468029151,\"lat\":-34.6042581302915},\"northeast\":{\"lng\":-58.37533671970849,\"lat\":-34.6015601697085}},\"bounds\":{\"southwest\":{\"lng\":-58.37669510000001,\"lat\":-34.6029093},\"northeast\":{\"lng\":-58.3766763,\"lat\":-34.602909}},\"location\":{\"lng\":-58.3766763,\"lat\":-34.602909},\"location_type\":\"RANGE_INTERPOLATED\"},\"address_components\":[{\"types\":[\"street_number\"],\"short_name\":\"458\",\"long_name\":\"458\"},{\"types\":[\"route\"],\"short_name\":\"Maipú\",\"long_name\":\"Maipú\"},{\"types\":[\"political\",\"sublocality\",\"sublocality_level_1\"],\"short_name\":\"San Nicolas\",\"long_name\":\"San Nicolas\"},{\"types\":[\"administrative_area_level_2\",\"political\"],\"short_name\":\"Comuna 1\",\"long_name\":\"Comuna 1\"},{\"types\":[\"administrative_area_level_1\",\"political\"],\"short_name\":\"CABA\",\"long_name\":\"Buenos Aires\"},{\"types\":[\"country\",\"political\"],\"short_name\":\"AR\",\"long_name\":\"Argentina\"},{\"types\":[\"postal_code\"],\"short_name\":\"C1006\",\"long_name\":\"C1006\"},{\"types\":[\"postal_code_suffix\"],\"short_name\":\"ACD\",\"long_name\":\"ACD\"}],\"place_id\":\"EiRNYWlww7ogNDU4LCBDMTAwNkFDRCBDQUJBLCBBcmdlbnRpbmE\"}]";
        return new JSONArray(jsonString);
    }

    public static JSONArray invalidJsonResults() throws JSONException{
        String jsonString= "[\"formatted_address\":\"157,, Av. Julio Argentino Roca 81, Villa Gral. Belgrano, Córdoba, Argentina\",\"types\":[\"bar\",\"establishment\",\"point_of_interest\"],\"partial_match\":true,\"geometry\":{\"viewport\":{\"southwest\":{\"lng\":-64.56059248029149,\"lat\":-31.9808230802915},\"northeast\":{\"lng\":-64.55789451970848,\"lat\":-31.9781251197085}},\"location\":{\"lng\":-64.5592435,\"lat\":-31.9794741},\"location_type\":\"ROOFTOP\"},\"address_components\":[{\"types\":[\"street_number\"],\"short_name\":\"81\",\"long_name\":\"81\"},{\"types\":[\"route\"],\"short_name\":\"Av. Julio Argentino Roca\",\"long_name\":\"Avenida Julio Argentino Roca\"},{\"types\":[\"locality\",\"political\"],\"short_name\":\"Villa Gral. Belgrano\",\"long_name\":\"Villa General Belgrano\"},{\"types\":[\"administrative_area_level_1\",\"political\"],\"short_name\":\"Córdoba\",\"long_name\":\"Córdoba\"},{\"types\":[\"country\",\"political\"],\"short_name\":\"AR\",\"long_name\":\"Argentina\"}],\"place_id\":\"ChIJ8XkLAEOw0pURYaNqB03sUJ8\"}]\n";
        return new JSONArray(jsonString);
    }
}