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
        
        Map<String, Object> d = tpc.getStatus(getSParameters());
        
        
        
        //Map<String, Object> a = tpc.sendAuthorizeRequest(parameters, getFraudControlParameters());
        
        System.out.println("TPC" + tpc);
        System.out.println("Parameters" + parameters);
        
        System.out.println("" + d);
                
    }
    
    private static Map<String, String> getFraudControlParameters() {

	Map<String, String> parameters = new HashMap<String, String>();
        
	parameters.put("CSBTCITY", "Villa General Belgrano"); //Ciudad de facturación, MANDATORIO.		
	parameters.put("CSBTCOUNTRY", "AR");//País de facturación. MANDATORIO. Código ISO. (http://apps.cybersource.com/library/documentation/sbc/quickref/countries_alpha_list.pdf)	
	parameters.put("CSBTCUSTOMERID", "453458"); //Identificador del usuario al que se le emite la factura. MANDATORIO. No puede contener un correo electrónico.		
	parameters.put("CSBTIPADDRESS", "192.0.0.4"); //IP de la PC del comprador. MANDATORIO.		
	parameters.put("CSBTEMAIL", "some@someurl.com"); //Mail del usuario al que se le emite la factura. MANDATORIO.
	parameters.put("CSBTFIRSTNAME", "Juan");//Nombre del usuario al que se le emite la factura. MANDATORIO.		
	parameters.put("CSBTLASTNAME", "Perez");//Apellido del usuario al que se le emite la factura. MANDATORIO.
	parameters.put("CSBTPHONENUMBER", "541160913988");//Teléfono del usuario al que se le emite la factura. No utilizar guiones, puntos o espacios. Incluir código de país. MANDATORIO.		
	parameters.put("CSBTPOSTALCODE", "1010");//Código Postal de la dirección de facturación. MANDATORIO.	
	parameters.put("CSBTSTATE", "B");//Provincia de la dirección de facturación. MANDATORIO. Ver tabla anexa de provincias.	
	parameters.put("CSBTSTREET1", "Some Street 2153");//Domicilio de facturación (calle y nro). MANDATORIO.			
	parameters.put("CSBTSTREET2", "Piso 8");//Complemento del domicilio. (piso, departamento). NO MANDATORIO.
	parameters.put("CSPTCURRENCY", "ARS");//Moneda. MANDATORIO.		
	parameters.put("CSPTGRANDTOTALAMOUNT", "125.38");//Con decimales opcional usando el puntos como separador de decimales. No se permiten comas, ni como separador de miles ni como separador de decimales. MANDATORIO.(Ejemplos:$125,38-> 125.38 $12-> 12 o 12.00)		
	parameters.put("CSMDD7", "");// Fecha registro comprador(num Dias). NO MANDATORIO.		
	parameters.put("CSMDD8", "Y"); //Usuario Guest? (Y/N). En caso de ser Y, el campo CSMDD9 no deberá enviarse. NO MANDATORIO.		
	parameters.put("CSMDD9", "");//Customer password Hash: criptograma asociado al password del comprador final. NO MANDATORIO.		
	parameters.put("CSMDD10", "");//Histórica de compras del comprador (Num transacciones). NO MANDATORIO.	
	parameters.put("CSMDD11", "");//Customer Cell Phone. NO MANDATORIO.		

	//Retail
	parameters.put("CSSTCITY", "Villa General Belgrano");//Ciudad de enví­o de la orden. MANDATORIO.	
	parameters.put("CSSTCOUNTRY", "AR");//País de envío de la orden. MANDATORIO.	
	parameters.put("CSSTEMAIL", "some@someurl.com");//Mail del destinatario, MANDATORIO.			
	parameters.put("CSSTFIRSTNAME", "Juan");//Nombre del destinatario. MANDATORIO.		
	parameters.put("CSSTLASTNAME", "Perez");//Apellido del destinatario. MANDATORIO.		
	parameters.put("CSSTPHONENUMBER", "541160913988");//Número de teléfono del destinatario. MANDATORIO.	
	parameters.put("CSSTPOSTALCODE", "1010");//Código postal del domicilio de envío. MANDATORIO.		
	parameters.put("CSSTSTATE", "B");//Provincia de envío. MANDATORIO. Son de 1 caracter			
	parameters.put("CSSTSTREET1", "Some Street 2153");//Domicilio de envío. MANDATORIO.		
	parameters.put("CSSTSTREET2", "Piso 8");//Complemento del domicilio. (piso, departamento). NO MANDATORIO.
	parameters.put("CSMDD12", "");//Shipping DeadLine (Num Dias). NO MADATORIO.		
	parameters.put("CSMDD13", "");//Método de Despacho. NO MANDATORIO.		
	parameters.put("CSMDD14", "");//Customer requires Tax Bill ? (Y/N). NO MANDATORIO.		
	parameters.put("CSMDD15", "");//Customer Loyality Number. NO MANDATORIO. 		
	parameters.put("CSMDD16", "");//Promotional / Coupon Code. NO MANDATORIO. 		
	
	//datos a enviar por cada producto, los valores deben estar separado con #:		
	parameters.put("CSITPRODUCTCODE", "electronic_good");//Código de producto. MANDATORIO. Valores posibles(adult_content;coupon;default;electronic_good;electronic_software;gift_certificate;handling_only;service;shipping_and_handling;shipping_only;subscription)	
	parameters.put("CSITPRODUCTDESCRIPTION", "Test Prd Description");//Descripción del producto. MANDATORIO.	
	parameters.put("CSITPRODUCTNAME", "TestPrd");//Nombre del producto. CONDICIONAL.	
	parameters.put("CSITPRODUCTSKU", "SKU1234");//Código identificador del producto. MANDATORIO.		
	parameters.put("CSITTOTALAMOUNT", "10.01");//CSITTOTALAMOUNT=CSITUNITPRICE*CSITQUANTITY "999999[.CC]" Con decimales opcional usando el punto como separador de decimales. No se permiten comas, ni como separador de miles ni como separador de decimales. MANDATORIO.		
	parameters.put("CSITQUANTITY", "1");//Cantidad del producto. CONDICIONAL.		
	parameters.put("CSITUNITPRICE", "10.01");//Formato Idem CSITTOTALAMOUNT. CONDICIONAL.	
        return null;
       
}
    
    private static Map<String, String> getSParameters(){
	Map<String, String> parameters = new HashMap<String, String>();
	parameters.put("Merchant", "12345678");
	parameters.put("OperationID", "8000");
	return parameters;
}	

    
    
}
