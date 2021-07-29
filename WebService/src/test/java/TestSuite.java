import com.niagara.provisioning.ws.jaxws.*;
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

    private static final ProvisioningWS provisioningWS = new ProvisioningWSImpl();
    private static Configuration newConfiguration;

    @Test
    @Order(1)
    public void createConfigurationTest() {
        newConfiguration = new Configuration();
        newConfiguration.setName("Theme");
        newConfiguration.setValue("Dark");
        newConfiguration.setDescription("Main theme");

        assertEquals(HttpStatus.SC_CREATED, provisioningWS.createConfiguration(newConfiguration).getResultCode());
    }

    @Test
    @Order(2)
    public void getCreatedConfigurationTest() {
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

        assertEquals(HttpStatus.SC_NOT_FOUND, provisioningWS.getConfiguration(configurationName).getResultCode());
    }
}
