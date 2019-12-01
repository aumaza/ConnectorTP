package main;

import mocks.FraudControlMockParameters;
import mocks.GoogleRestTodoPagoMock;

import org.json.JSONException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GoogleRestTodoPagoTest {
	
	private Map<String,String> cfParamsOriginales=FraudControlMockParameters.getFraudControlParameters("villa general belgrano","argentina","cordoba","Av. Julio Argentino Roca 81","1006","san nicolas","argentina","buenos aires","maipu 458","1010");
	private Map<String,String> cfParamsModificados=FraudControlMockParameters.getFraudControlParameters("Villa General Belgrano","Argentina","Córdoba","Av. Julio Argentino Roca 81","C1006","San Nicolas","Argentina","Buenos Aires","Maipu 458","1010");
	
	@Test
	public void googleCallOK() throws UnsupportedEncodingException, JSONException {

		Map<String,String> fraudcontrolOriginal=this.cfParamsOriginales;		
		GoogleRestTodoPagoMock grtpm=new GoogleRestTodoPagoMock(this.cfParamsOriginales.get("CSBTSTREET1"),this.cfParamsOriginales.get("CSSTSTREET1"),this.cfParamsModificados);
		Map<String,String> fraudcontrolModificado=grtpm.processData(fraudcontrolOriginal);
		
		assertEquals("Argentina",fraudcontrolModificado.get("CSBTCOUNTRY"));
		assertEquals("Córdoba",fraudcontrolModificado.get("CSBTSTATE"));
		assertEquals("Av. Julio Argentino Roca 81",fraudcontrolModificado.get("CSBTSTREET1"));
		assertEquals("C1006",fraudcontrolModificado.get("CSBTPOSTALCODE"));
		
		assertEquals("Argentina",fraudcontrolModificado.get("CSSTCOUNTRY"));	
		assertEquals("Buenos Aires",fraudcontrolModificado.get("CSSTSTATE"));
		assertEquals("Maipu 458",fraudcontrolModificado.get("CSSTSTREET1"));
		assertEquals("1010",fraudcontrolModificado.get("CSSTPOSTALCODE"));
	}

}