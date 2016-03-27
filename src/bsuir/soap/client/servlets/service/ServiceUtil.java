package bsuir.soap.client.servlets.service;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


public class ServiceUtil {
	
	 private static Service service;
	    
	    static {
	    	URL url = null;
			try {
				url = new URL("http://localhost:9999/handbook?wsdl");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			QName qname = new QName("http://handbook.soap.webService/", "HandBookJSImplService");
			service = Service.create(url, qname);
	    }

	    public static Service getServiceSOAP() {
	        return service;

	    }

}
