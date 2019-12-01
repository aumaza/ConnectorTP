package ar.com.todopago.api.model;

import java.util.HashMap;
import java.util.Map;

import ar.com.todopago.api.ElementNames;

public class TransactionBSA {
	
	// Request
    private Map<String, Object> generalData;
    private Map<String, Object> operationData;
    private Map<String, Object> technicalData;

    // Response
    private String channel;
    private String urlHibridFormResuorces;
    private String publicRequestKey;
    private String requestKey; 
    private String transactionID;

    public TransactionBSA(){}

    public TransactionBSA(Map<String, Object> generalData, Map<String, Object> operationData, Map<String, Object> technicalData)
    {
        this.generalData = generalData;
        this.operationData = operationData;
        this.technicalData = technicalData;
    }

    public Map<String, Object> getGeneralData(){
        return this.generalData;
    }

    public void setGeneralData(Map<String, Object> generalData){
        this.generalData = generalData;
    }

    public Map<String, Object> getOperationData() {
        return this.operationData;
    }

    public void setOperationData(Map<String, Object> operationData){
        this.operationData = operationData;
    }

    public Map<String, Object> getTecnicalData(){
        return this.technicalData;
    }

    public void setTecnicalData(Map<String, Object> tecnicalData){
        this.technicalData = tecnicalData;
    }

    public String getChannel(){
        return this.channel;
    }

    public void setChannel(String channel){
        this.channel = channel;
    }

    public String getUrlHibridFormResuorces(){
        return this.urlHibridFormResuorces;
    }

    public void setUrlHibridFormResuorces(String urlHibridFormResuorces) {
        this.urlHibridFormResuorces = urlHibridFormResuorces;
    }

    public String getPublicRequestKey(){
        return this.publicRequestKey;
    }

    public void setPublicRequestKeys(String publicRequestKey) {
        this.publicRequestKey = publicRequestKey;
    }

    public String getRequestKey() {
        return this.requestKey;
    }

    public void setRequestKey(String requestKey){
        this.requestKey = requestKey;
    }

    public String getTransactionID() {
        return this.transactionID;
    }

    public void setTransactionID(String transactionID){
        this.transactionID = transactionID;
    }

    public String toString(){
    	String result = "TransactionBSA [" + ElementNames.BSA_CHANNEL + " = " + channel + ", " + ElementNames.BSA_URL_HIBRID_FROM_RESOURCES + " = " + urlHibridFormResuorces;
        result = result + ", " + ElementNames.BSA_PUBLIC_REQUEST_KEY + " = " + publicRequestKey + ", " + ElementNames.BSA_REQUEST_KEY + " = " + requestKey;
        result = result + ", " + ElementNames.BSA_TRANSACTION_ID_RESPONSE + " = " + transactionID + " ]";
        return result;
    }

    public Map<String, Object> toMap(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put(ElementNames.BSA_CHANNEL, this.channel);
    	map.put(ElementNames.BSA_URL_HIBRID_FROM_RESOURCES, this.urlHibridFormResuorces);
    	map.put(ElementNames.BSA_PUBLIC_REQUEST_KEY, this.publicRequestKey);
    	map.put(ElementNames.BSA_REQUEST_KEY, this.requestKey);
    	map.put(ElementNames.BSA_TRANSACTION_ID_RESPONSE, this.transactionID);
        return map;
    }

}
