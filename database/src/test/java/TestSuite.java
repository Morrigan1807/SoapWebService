import lombok.extern.log4j.Log4j2;
import model.SinglevaluedConfiguration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import repository.SinglevaluedConfigurationRepository;
import repository.SinglevaluedConfigurationRepositoryMySql;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSuite {

    private static final SinglevaluedConfigurationRepository repository = new SinglevaluedConfigurationRepositoryMySql();
    private static SinglevaluedConfiguration newConfiguration;

    @Test
    @Order(1)
    public void createConfigurationTest() {
        newConfiguration = new SinglevaluedConfiguration();
        newConfiguration.setAttributeName("Theme");
        newConfiguration.setAttributeValue("Dark");
        newConfiguration.setAttributeDesc("Main theme");

        assertDoesNotThrow(() -> repository.insert(newConfiguration));
    }

    @Test
    @Order(2)
    public void getCreatedConfigurationTest() {
        SinglevaluedConfiguration receivedConfiguration = repository.selectByAttributeName(newConfiguration.getAttributeName());
        assertEquals(newConfiguration.getAttributeName(), receivedConfiguration.getAttributeName());
        assertEquals(newConfiguration.getAttributeValue(), receivedConfiguration.getAttributeValue());
        assertEquals(newConfiguration.getAttributeValue(), receivedConfiguration.getAttributeValue());
    }

    @Test
    @Order(3)
    public void updateCreatedConfigurationTest() {
        newConfiguration.setAttributeValue("Light");
        newConfiguration.setAttributeDesc("Main theme of design");
        assertDoesNotThrow(() -> repository.update(newConfiguration));
    }

    @Test
    @Order(4)
    public void getUpdatedConfigurationTest() {
        SinglevaluedConfiguration receivedConfiguration = repository.selectByAttributeName(newConfiguration.getAttributeName());
        assertEquals(newConfiguration.getAttributeName(), receivedConfiguration.getAttributeName());
        assertEquals(newConfiguration.getAttributeValue(), receivedConfiguration.getAttributeValue());
        assertEquals(newConfiguration.getAttributeValue(), receivedConfiguration.getAttributeValue());
    }

    @Test
    @Order(5)
    public void deleteUpdatedConfigurationTest() {
        assertDoesNotThrow(() -> repository.delete(newConfiguration));
    }

    @Test
    @Order(6)
    public void checkConfigurationNotExistingTest() {
        assertNull(repository.selectByAttributeName(newConfiguration.getAttributeName()));
    }
}
