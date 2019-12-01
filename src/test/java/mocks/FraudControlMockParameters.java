package mocks;

import java.util.HashMap;
import java.util.Map;

public class FraudControlMockParameters {
	// Parametros para el control de fraude
	public static Map<String, String> getFraudControlParameters(String csbtcity,String csbtcountry,String csbtstate,String csbtstreet1,String csbtpostalcode,String csstcity,String csstcountry,String csststate,String csststreet1,String csstpostalcode){
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("CSBTCITY",csbtcity); // MANDATORIO.
		parameters.put("CSBTCOUNTRY",csbtcountry);// MANDATORIO. C�digo ISO.
		parameters.put("CSBTEMAIL", "some@someurl.com"); // MANDATORIO.
		parameters.put("CSBTFIRSTNAME", "juan");// MANDATORIO.
		parameters.put("CSBTLASTNAME", "Perez");// MANDATORIO.
		parameters.put("CSBTPHONENUMBER", "541160913988");// MANDATORIO.
		parameters.put("CSBTPOSTALCODE",csbtpostalcode);// MANDATORIO.
		parameters.put("CSBTSTATE",csbtstate);// MANDATORIO
		parameters.put("CSBTSTREET1", csbtstreet1);// MANDATORIO.
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

		switch (TodoPagoParametersMock.vertical) {
		case TodoPagoParametersMock.RETAIL:
			setRetail(parameters,csstcity,csstcountry,csststate,csststreet1,csstpostalcode);
			break;
		}

		return parameters;
	}
	
	public static void setRetail(Map<String, String> parameters,String csstcity,String csstcountry,String csststate,String csststreet1,String csstpostalcode) {
		parameters.put("CSSTCITY",csstcity); // MANDATORIO.
		parameters.put("CSSTCOUNTRY", csstcountry);// MANDATORIO. C�digo ISO.
		parameters.put("CSSTEMAIL", "some@someurl.com"); // MANDATORIO.
		parameters.put("CSSTFIRSTNAME", "Juan");// MANDATORIO.
		parameters.put("CSSTLASTNAME", "Perez");// MANDATORIO.
		parameters.put("CSSTPHONENUMBER", "541160913988");// MANDATORIO.
		parameters.put("CSSTPOSTALCODE",csstpostalcode);// MANDATORIO.
		parameters.put("CSSTSTATE",csststate);// MANDATORIO
		parameters.put("CSSTSTREET1",csststreet1);// MANDATORIO.
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
}
