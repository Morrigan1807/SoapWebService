package com.niagara.provisioning.ws.jaxws;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class SOAPPublisherClient {

    public static void main(String[] args) throws MalformedURLException {
        URL wsdlURL = new URL("http://localhost:8080/niagara/ws?wsdl");

        QName qname = new QName("http://com.niagara.provisioning.ws", "ProvisioningWSImplService");

        Service service = Service.create(wsdlURL, qname);

        ProvisioningWS ws = service.getPort(ProvisioningWS.class);

        ConfigurationName name = new ConfigurationName();
        name.setName("CC");
        Configuration configuration = ws.getConfiguration(name).getConfiguration();
        System.out.println(configuration.getName());
        System.out.println(configuration.getValue());
        System.out.println(configuration.getDescription());
    }
}
