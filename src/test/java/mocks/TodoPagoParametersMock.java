package mocks;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.todopago.api.ElementNames;

public class TodoPagoParametersMock {
	
	public final static int RETAIL = 0;
	public static int vertical = RETAIL;// Configurar vertical a usar
	
	public static final String APIKEY = "TODOPAGO 0b27699db496431a8391096be5ffc";
	public static final String MERCHANT = "157";
	public static final String SECURITY = "0b27699db496431a8391096be5ffc";
	
	public static Map<String, List<String>> getAuthorization() {
		Map<String, List<String>> parameters = new HashMap<String, List<String>>();
		parameters.put(ElementNames.Authorization, Collections.singletonList(APIKEY));
		
		return parameters;
	}
	
}