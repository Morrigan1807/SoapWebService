package com.niagara.provisioning.ws.jaxws;

import lombok.extern.log4j.Log4j2;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@Log4j2
public class SOAPPublisherClient {

    public static void main(String[] args) throws MalformedURLException {
        URL wsdlURL = new URL("http://localhost:8080/niagara/ws?wsdl");
        QName qname = new QName("http://com.niagara.provisioning.ws", "ProvisioningWSImplService");
        Service service = Service.create(wsdlURL, qname);
        ProvisioningWS ws = service.getPort(ProvisioningWS.class);

        ConfigurationName name = new ConfigurationName();
        name.setName("CC");
        Configuration configuration = ws.getConfiguration(name).getConfiguration();

        log.info("Received configuration:");
        log.info(String.format("Name: %s", configuration.getName()));
        log.info(String.format("Value: %s", configuration.getValue()));
        log.info(String.format("Description: %s", configuration.getDescription()));
    }
}
