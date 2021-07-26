import com.niagara.provisioning.ws.jaxws.Configuration;
import com.niagara.provisioning.ws.jaxws.ListOfConfigurations;
import model.SinglevaluedConfiguration;

import java.util.List;

public class Util {

    private Util() {
    }

    public static SinglevaluedConfiguration convertConfigurationFromServiceToDb(Configuration configurationService) {
        return SinglevaluedConfiguration.builder()
                .attributeName(configurationService.getName())
                .attributeValue(configurationService.getValue())
                .attributeDesc(configurationService.getDescription())
                .build();
    }

    public static Configuration convertConfigurationFromDbToService(SinglevaluedConfiguration configurationDb) {
        Configuration configurationService = new Configuration();

        configurationService.setName(configurationDb.getAttributeName());
        configurationService.setValue(configurationDb.getAttributeValue());
        configurationService.setDescription(configurationDb.getAttributeDesc());

        return configurationService;
    }

    public static ListOfConfigurations convertListOfConfigurationsFromDbToService(List<SinglevaluedConfiguration> configurationsDb) {
        ListOfConfigurations listOfConfigurationsService = new ListOfConfigurations();

        configurationsDb.forEach(configurationDb -> listOfConfigurationsService.getConfigurations().add(convertConfigurationFromDbToService(configurationDb)));

        return listOfConfigurationsService;
    }
}
