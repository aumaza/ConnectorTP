package main;

import mocks.GoogleRestMock;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class GoogleRestTest {
		
	private String key="AIzaSyAXRp4mRP(v8fgPEK_GEbO7TuApET52iij";
	
	@Test
	public void googleCallOK() throws UnsupportedEncodingException, JSONException {

		GoogleRestMock grm=new GoogleRestMock("Julio Argentino Roca",this.key,GoogleRestMock.validJsonResults());
		
		JSONArray receivedData=grm.getGoogleAdressData();
		JSONArray adressComponents=(JSONArray)receivedData.getJSONObject(0).get("address_components");
		
		assertEquals("157,, Av. Julio Argentino Roca 81, Villa Gral. Belgrano, Córdoba, Argentina", receivedData.getJSONObject(0).get("formatted_address"));
		assertEquals("Avenida Julio Argentino Roca",adressComponents.getJSONObject(1).get("long_name"));
		assertEquals("Villa General Belgrano",adressComponents.getJSONObject(2).get("long_name") );
		assertEquals("Córdoba",adressComponents.getJSONObject(3).get("long_name") );
		assertEquals("Argentina",adressComponents.getJSONObject(4).get("long_name") );	
	}
	
	@Test(expected=JSONException.class)
	public void jsonException() throws UnsupportedEncodingException, JSONException {

		GoogleRestMock grm=new GoogleRestMock("Julio Argentino Roca",this.key,GoogleRestMock.invalidJsonResults());
		
		JSONArray receivedData=grm.getGoogleAdressData();
		JSONArray adressComponents=(JSONArray)receivedData.getJSONObject(0).get("address_components");
		
		assertEquals("157,, Av. Julio Argentino Roca 81, Villa Gral. Belgrano, Córdoba, Argentina", receivedData.getJSONObject(0).get("formatted_address"));
		assertEquals("Avenida Julio Argentino Roca",adressComponents.getJSONObject(1).get("long_name"));		
	}
}