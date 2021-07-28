import com.niagara.provisioning.ws.jaxws.Configuration;
import com.niagara.provisioning.ws.jaxws.ConfigurationName;
import com.niagara.provisioning.ws.jaxws.ProvisioningWS;
import com.niagara.provisioning.ws.jaxws.ResponseMessageWithConfiguration;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSuite {

    private static final String WSDL_URL = "http://localhost:8080/niagara/ws?wsdl";
    private static final String NAMESPACE_URL = "http://com.niagara.provisioning.ws";
    private static final String LOCAL_PART = "ProvisioningWSImplService";
    private static ProvisioningWS provisioningWS;
    private static Configuration newConfiguration;

    @BeforeAll
    public static void prepareServiceAndNewConfiguration() {
        try {
            URL wsdlURL = new URL(WSDL_URL);
            QName qname = new QName(NAMESPACE_URL, LOCAL_PART);
            Service service = Service.create(wsdlURL, qname);
            provisioningWS = service.getPort(ProvisioningWS.class);
        }
        catch (MalformedURLException exception) {
            log.error(exception.getMessage());
        }
    }

    @Test
    @Order(1)
    public void createConfigurationTest() {
        newConfiguration = new Configuration();
        newConfiguration.setName("Theme");
        newConfiguration.setValue("Dark");
        newConfiguration.setDescription("Main theme");

        assertEquals(HttpStatus.SC_OK, provisioningWS.createConfiguration(newConfiguration).getResultCode());
    }

    @Test
    @Order(2)
    public void getCreatedConfigurationTest() {
        ConfigurationName configurationName = new ConfigurationName();
        configurationName.setName(newConfiguration.getName());

        ResponseMessageWithConfiguration response = provisioningWS.getConfiguration(configurationName);
        assertEquals(200, response.getResultCode());

        Configuration receivedConfiguration = response.getConfiguration();
        assertEquals(newConfiguration.getName(), receivedConfiguration.getName());
        assertEquals(newConfiguration.getValue(), receivedConfiguration.getValue());
        assertEquals(newConfiguration.getDescription(), receivedConfiguration.getDescription());
    }

    @Test
    @Order(3)
    public void updateCreatedConfigurationTest() {
        newConfiguration.setValue("Light");
        newConfiguration.setDescription("Main theme of design");
        assertEquals(HttpStatus.SC_OK, provisioningWS.updateConfiguration(newConfiguration).getResultCode());
    }

    @Test
    @Order(4)
    public void getUpdatedConfigurationTest() {
        ConfigurationName configurationName = new ConfigurationName();
        configurationName.setName(newConfiguration.getName());

        ResponseMessageWithConfiguration response = provisioningWS.getConfiguration(configurationName);
        assertEquals(HttpStatus.SC_OK, response.getResultCode());

        Configuration receivedConfiguration = response.getConfiguration();
        assertEquals(newConfiguration.getName(), receivedConfiguration.getName());
        assertEquals(newConfiguration.getValue(), receivedConfiguration.getValue());
        assertEquals(newConfiguration.getDescription(), receivedConfiguration.getDescription());
    }

    @Test
    @Order(5)
    public void deleteUpdatedConfigurationTest() {
        assertEquals(HttpStatus.SC_NO_CONTENT, provisioningWS.deleteConfiguration(newConfiguration).getResultCode());
    }

    @Test
    @Order(6)
    public void checkConfigurationNotExistingTest() {
        ConfigurationName configurationName = new ConfigurationName();
        configurationName.setName(newConfiguration.getName());

        assertThrows(ServerSOAPFaultException.class, () -> provisioningWS.getConfiguration(configurationName));
    }
}
