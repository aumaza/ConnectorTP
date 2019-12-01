package ar.com.todopago.api.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import ar.com.todopago.api.exceptions.ResponseException;
import ar.com.todopago.api.model.User;

public class OperationsParser {

	private final static Logger logger = Logger.getLogger(OperationsParser.class.getName());
	
	// from InputStream to Map
	public static Map<String, Object> inputStreamToMap(InputStream inputStream)
			throws ParserConfigurationException, SAXException, IOException {
		
		String line;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		while ((line = br.readLine()) != null) {
			sb.append(line.trim());
		}
	    
		logger.log(Level.WARNING,sb.toString());
		NodeList nList = inputStreamToNodeList(sb.toString());
		return parseNodeListToMap(nList);
	}

	// From InputStream to NodeList
	public static NodeList inputStreamToNodeList(String xml)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		Document doc = dBuilder.parse(is);
		doc.getDocumentElement().normalize();
		return doc.getChildNodes();
	}

	// From NodeList to Map
	public static Map<String, Object> parseNodeListToMap(NodeList nList) {
		Map<String, Object> ret = new HashMap<String, Object>();
		for (int i = 0; i < nList.getLength(); i++) {
			if (nList.item(i).hasChildNodes() && nList.item(i).getChildNodes().item(0).hasChildNodes()) {
				// call recursive, fill a new map
			    // logger.log(Level.WARNING,
				// nList.item(i).getNodeName().toString() + " - "+
				// nList.item(i).getChildNodes().toString() );
				if (nList.item(i).getNodeName().equals("ITEM")) {
					ret.put(nList.item(i).getNodeName() + i, parseNodeListToMap(nList.item(i).getChildNodes()));
					// logger.log(Level.WARNING,
					// nList.item(i).getNodeName().toString() + i + " - "+
					// nList.item(i).getChildNodes().toString() );
				} else if (nList.item(i).getNodeName().equals("REFUND")) {
					ret.put(nList.item(i).getNodeName() + i, parseNodeListToMap(nList.item(i).getChildNodes()));
					// logger.log(Level.WARNING,
					// nList.item(i).getNodeName().toString() + i + " - "+
					// nList.item(i).getChildNodes().toString() );
				} else if (nList.item(i).getNodeName().equals("Operations")) {
					ret.put(nList.item(i).getNodeName() + i, parseNodeListToMap(nList.item(i).getChildNodes()));
				} else {
					if (nList.item(i).getNodeName().equals("Operations")) {
						ret.put(nList.item(i).getNodeName() + " - " + (i + 1), parseNodeListToMap(nList.item(i).getChildNodes()));
					} else {
						ret.put(nList.item(i).getNodeName(), parseNodeListToMap(nList.item(i).getChildNodes()));
					}
				}
			} else {
				// add value to this map
				// logger.log(Level.WARNING,
				// nList.item(i).getNodeName().toString() + " - "+
				// nList.item(i).getTextContent().toString() );
				ret.put(nList.item(i).getNodeName(), nList.item(i).getTextContent());
			}
		}
		return ret;
	}

	public static Map<String, Object> parseInputStreamJsonToMap(InputStream inputStream)
			throws JSONException, IOException {
		Map<String, Object> outMap = new HashMap<String, Object>();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String output = br.readLine();
		JSONObject jsonObject = new JSONObject(output);
		outMap = generateMap(jsonObject, outMap, null);
		return outMap;
	}
	
	public static Map<String, Object> parseInputStreamJsonGoogleToMap(InputStream inputStream)
			throws JSONException, IOException {
		Map<String, Object> outMap = new HashMap<String, Object>();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String output = br.readLine();
		StringBuilder sb=new StringBuilder();
		
		
		while(output!=null){
			sb.append(output);
			output=br.readLine();
		}
		
		outMap=jsonStringToMap(sb.toString());
	
		return outMap;
	}
	
	public static Map<String, Object> jsonStringToMap(String jsonString) throws JSONException {

        JSONObject jsonObject=new JSONObject(jsonString);
        
        Map<String,Object> map=new HashMap<String, Object>();
        
        map.put("results",jsonObject);
        
        return map; 
    }

	private static Map<String, Object> generateMap(JSONObject jsonObject, Map<String, Object> outMap, String key)
			throws JSONException {

		Map<String, Object> auxMap = new HashMap<String, Object>();
		Iterator<String> Itr = jsonObject.keys();

		while (Itr.hasNext()) {
			String name = Itr.next();
			String a = jsonObject.getString(name);
			if (a.contains("{")) {
				JSONObject jsonObject2 = (JSONObject) jsonObject.get(name);
				auxMap.put(name, null);
				generateMap(jsonObject2, auxMap, name);
			} else {
				auxMap.put(name, jsonObject.getString(name));
			}
		}
		if (key != null) {
			outMap.put(key, auxMap);
		} else {
			outMap.putAll(auxMap);
		}
		return outMap;
	}

	public static User parseInputStreamJsonToUser(InputStream inputStream)
			throws JSONException, IOException, ResponseException {
		User user = new User();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String output = br.readLine();
		user = parseInputStreamJsonToUser(new JSONObject(output));
		return user;
	}

	public static User parseInputStreamJsonToUser(JSONObject jsonObject)
			throws JSONException, IOException, ResponseException {

		JSONObject jCredentials;
		JSONObject jresultado;

		User user = new User();
		jCredentials = jsonObject.getJSONObject("Credentials");
		jresultado = jCredentials.getJSONObject("resultado");

		if (jCredentials.getInt("codigoResultado") == 1) {
			if (jresultado.getInt("codigoResultado") != 0) {
				throw new ResponseException(
						jresultado.getString("mensajeKey") + " - " + jresultado.getString("mensajeResultado"));
			} else {
				user.setApiKey(jCredentials.getString("APIKey"));
				user.setMerchant(jCredentials.getString("merchantId"));
			}
		} else {
			// throw new ResponseException("");
		}
		return user;
	}

}
