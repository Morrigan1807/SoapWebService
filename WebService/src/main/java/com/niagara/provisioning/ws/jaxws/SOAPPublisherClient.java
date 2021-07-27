package com.niagara.provisioning.ws.jaxws;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class SOAPPublisherClient {

    public static void main(String[] args) throws MalformedURLException {
        URL wsdlURL = new URL("https://localhost:8080/niagara/ws?wsdl");
        //check above URL in browser, you should see WSDL file

        //creating QName using targetNamespace and name
        QName qname = new QName("https://localhost:8080/niagara/", "ws");

        Service service = Service.create(wsdlURL, qname);

        //We need to pass interface and model beans to client
        ProvisioningWSImpl ws = service.getPort(ProvisioningWSImpl.class);

        ConfigurationName name = new ConfigurationName();
        name.setName("CC");
        System.out.println(ws.getConfiguration(name));
    }

}
