/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.todopago.api;

//Paquetes basicos de java
import java.util.*;
import java.io.*;

//Paquetes propios del SDK TODO PAGO
import ar.com.todopago.utils.*;
import ar.com.todopago.api.rest.*;
import ar.com.todopago.api.authorize.*;
import ar.com.todopago.api.echoservice.*;
import ar.com.todopago.api.exceptions.*;
import ar.com.todopago.api.model.*;
import ar.com.todopago.utils.*;
import java.net.MalformedURLException;
import org.hamcrest.*;
import org.ietf.jgss.*;
import org.jcp.xml.dsig.internal.*;
import org.json.*;
import org.mockito.*;
import org.objenesis.*;
import org.w3c.dom.*;
import org.wso2.ws.dataservice.*;
import org.xml.sax.*;


/**
 *
 * @author augusto
 */
public class Connector extends TodoPagoConector{
    
    public Connector(int endpoint, Map<String, List<String>> auth) throws MalformedURLException {
        super(endpoint, auth);
    }
    
    public static void main(String[] args) throws MalformedURLException{
        
        //se crea el mapa de autorizacion
        Map<String, List<String>> auth = new HashMap<String, List<String>>();
        auth.put(ElementNames.Authorization, Collections.singletonList("PRISMA f3d8b72c94ab4a06be2ef7c95490f7d3"));
        
        //se crea el conector a todo pago
        TodoPagoConector tpc = new TodoPagoConector(TodoPagoConector.developerEndpoint,auth);
        
        //Se crea el mapa de parámetros para validar y se inicializan los parametros
        Map<String, String> parameters = new HashMap<String,String>();
        parameters.put(ElementNames.Session, "ABCDEF-1234-12221-FDE1-00000200");
        parameters.put(ElementNames.Security,  "1234567890ABCDEF1234567890ABCDEF");
        parameters.put(ElementNames.EncodingMethod, "XML");
        parameters.put(ElementNames.Merchant, "123456778");
        parameters.put(ElementNames.OperationID, "0800");
        parameters.put(ElementNames.CurrencyCode, "032");
        parameters.put(ElementNames.Amount, "1.00");
        parameters.put(ElementNames.UrlOK, "http,//someur1.com/ok/");
        parameters.put(ElementNames.UrlError, "http,//someur1.com/fail/");
        parameters.put(ElementNames.EMAILCLIENTE, "some@smoeurl.com");
        
                
        
        
        Map<String, Object> a = tpc.sendAuthorizeRequest(parameters, getFraudControlParameters());
        
//System.out.println("tpc");
    }

    
    
}
