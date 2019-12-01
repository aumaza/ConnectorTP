package ar.com.todopago.api.rest;

import java.util.List;
import java.util.Map;

public class BSARest extends RestConnector{
	
	public BSARest(String endpoint, Map<String, List<String>> headder) {
		super(endpoint, headder);
	}

}
